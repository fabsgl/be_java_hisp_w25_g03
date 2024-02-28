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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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

    @BeforeEach
    void setUp() {
        vendorUser = new User(1, "agustin", List.of(2, 3), List.of(), VENDOR);
        clientUser = new User(2, "diego", List.of(), List.of(1, 3), CLIENT);
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
    @Test
    void getVendorFollowersOrderNullOk() {
        //Arrange
        List<User> usuariosArrange = List.of(clientUser, new User(3, "facundo", List.of(), List.of(1), CLIENT));
        when(userRepository.getAllFollowers(any())).thenReturn(usuariosArrange); //any() reemplaza List.of(2, 3)
        FollowedListDto expectedFollowedList =
                new FollowedListDto(1, "agustin",
                                usuariosArrange.stream()
                                .map(UserVendorDto::new)
                                .toList());

        FollowedListDto actual = userService.getVendorFollowers(1, null);   //act
        assertEquals(expectedFollowedList, actual);                                     //assertion
    }
    @Test
    void getVendorFollowersOrderNameAscOK(){
        List<User> usuariosArrange = List.of(clientUser, new User(3, "facundo", List.of(), List.of(1), CLIENT));
        when(userRepository.getAllFollowers(any())).thenReturn(usuariosArrange);

        FollowedListDto expectedFollowedList =
                new FollowedListDto(1, "agustin",
                        usuariosArrange.stream()
                                .map(UserVendorDto::new)
                                .sorted(Comparator.comparing(UserVendorDto::getUserName))
                                .toList());
        FollowedListDto actual = userService.getVendorFollowers(1, "name_asc");   //act
        assertEquals(expectedFollowedList, actual);                                            //assertion
    }
    @Test
    void getVendorFollowersOrderNameDescOK(){
        List<User> usuariosArrange = List.of(clientUser, new User(3, "facundo", List.of(), List.of(1), CLIENT));
        when(userRepository.getAllFollowers(any())).thenReturn(usuariosArrange);

        FollowedListDto expectedFollowedList =
                new FollowedListDto(1, "agustin",
                        usuariosArrange.stream()
                                .map(UserVendorDto::new)
                                .sorted(Comparator.comparing(UserVendorDto::getUserName).reversed())
                                .toList());
        FollowedListDto actual = userService.getVendorFollowers(1, "name_desc");   //act
        assertEquals(expectedFollowedList, actual);                                            //assertion
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