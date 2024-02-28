package com.socialMeli.service;

import com.socialMeli.dto.response.FollowedListDto;
import com.socialMeli.dto.response.UserVendorDto;
import com.socialMeli.dto.response.VendorFollowCountDto;
import com.socialMeli.entity.User;
import com.socialMeli.entity.UserType;
import com.socialMeli.exception.InvalidDataException;
import com.socialMeli.exception.NotFoundException;
import com.socialMeli.exception.UserIsNotVendorException;
import com.socialMeli.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.socialMeli.entity.UserType.CLIENT;
import static com.socialMeli.entity.UserType.VENDOR;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    IUserRepository userRepository;
    @InjectMocks
    UserService userService;
    User vendorUser;
    User clientUser;
    List<User> usuariosArrange;
    List<UserVendorDto> usuariosArrangeOrderAsc;
    List<UserVendorDto> usuariosArrangeOrderDesc;
    List<UserVendorDto> usuariosArrangeOrderNull;

    static Stream<Arguments> validFollowersValues(){
        return Stream.of(Arguments.of("name_asc"),Arguments.of("name_desc"),null);
    }

    @BeforeEach
    void setUp() {
        vendorUser = new User(1, "agustin", List.of(2, 3), List.of(), VENDOR);
        clientUser = new User(2, "diego", List.of(), List.of(1, 3), CLIENT);
        usuariosArrange = List.of(  clientUser,
                                    new User(3, "facundo", List.of(), List.of(1), CLIENT),
                                    new User(4, "fabian", List.of(), List.of(1), CLIENT));

        usuariosArrangeOrderNull = new ArrayList<>(List.of( new UserVendorDto(2,"diego"),
                                                            new UserVendorDto(3, "facundo"),
                                                            new UserVendorDto(4,"fabian")));

        usuariosArrangeOrderAsc = new ArrayList<>(List.of(  new UserVendorDto(2,"diego"),
                                                            new UserVendorDto(4,"fabian"),
                                                            new UserVendorDto(3,"facundo")));

        usuariosArrangeOrderDesc = new ArrayList<>(List.of( new UserVendorDto(3,"facundo"),
                                                            new UserVendorDto(4,"fabian"),
                                                            new UserVendorDto(2,"diego")));
        when(userRepository.findUserByUserId(anyInt())).thenReturn(Optional.of(vendorUser));
    }

    @Test
    void getFollowerCountOkTest() {
        VendorFollowCountDto expectedVendorFollowerAccount = new VendorFollowCountDto(vendorUser);
        VendorFollowCountDto vendorFollowCountDto = userService.getFollowerCount(1);
        assertEquals(expectedVendorFollowerAccount, vendorFollowCountDto);
    }
    @Test
    void getFollowerCountUserNotFoundTest() {
        when(userRepository.findUserByUserId(anyInt())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userService.getFollowerCount(0));
    }
    @Test
    void getFollowerCountUserNotVendorTest() {
        when(userRepository.findUserByUserId(anyInt())).thenReturn(Optional.of(clientUser));
        assertThrows(UserIsNotVendorException.class, () -> userService.getFollowerCount(2));
    }

    //T-0003 y T-0004 -> US-0003
    @ParameterizedTest
    @MethodSource("validFollowersValues")
    void getVendorFollowersOrderNullOk(String order) {
        //Arrange
        FollowedListDto expectedFollowedList;
        if (order == null){
            expectedFollowedList = new FollowedListDto(1,"agustin",usuariosArrangeOrderNull);
        }else if(order.equalsIgnoreCase("name_asc")) {
            expectedFollowedList = new FollowedListDto(1,"agustin",usuariosArrangeOrderAsc);
        }else {
            expectedFollowedList = new FollowedListDto(1,"agustin",usuariosArrangeOrderDesc);
        }
        when(userRepository.getAllFollowers(any())).thenReturn(usuariosArrange);
        FollowedListDto actual = userService.getVendorFollowers(1, order);   //act
        assertEquals(expectedFollowedList, actual);                                 //assertion
    }
    @Test
    void getVendorFollowersNotFoundTest() {
        when(userRepository.findUserByUserId(anyInt())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userService.getVendorFollowers(0,null));
    }
    @Test
    void getVendorFollowersUserNotVendorTest() {
        when(userRepository.findUserByUserId(anyInt())).thenReturn(Optional.of(clientUser));
        assertThrows(UserIsNotVendorException.class, () -> userService.getVendorFollowers(2,null));
    }
    @Test
    void getVendorFollowersInvalidDataExceptionTest(){
        when(userRepository.getAllFollowers(any())).thenReturn(new ArrayList<>());
        assertThrows(InvalidDataException.class, () -> userService.getVendorFollowers(2,"flsdmfr"));
    }
}