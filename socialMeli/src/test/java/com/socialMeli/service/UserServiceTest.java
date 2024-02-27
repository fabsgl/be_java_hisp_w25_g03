package com.socialMeli.service;

import com.socialMeli.dto.response.VendorFollowCountDto;
import com.socialMeli.entity.User;
import com.socialMeli.entity.UserType;
import com.socialMeli.exception.NotFoundException;
import com.socialMeli.exception.UserIsNotVendorException;
import com.socialMeli.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.socialMeli.entity.UserType.CLIENT;
import static com.socialMeli.entity.UserType.VENDOR;
import static org.junit.jupiter.api.Assertions.*;
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
        clientUser = new User(2,"diego",List.of(),List.of(1,3), CLIENT);
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
        assertThrows(NotFoundException.class,() -> userService.getFollowerCount(0));
    }
    @Test
    void getFollowerCountUserNotVendorTest() {
        when(userRepository.findUserByUserId(anyInt())).thenReturn(Optional.of(clientUser));
        assertThrows(UserIsNotVendorException.class,() -> userService.getFollowerCount(2));
    }
}