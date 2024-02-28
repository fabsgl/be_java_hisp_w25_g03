package com.socialMeli.service;

import com.socialMeli.dto.response.FollowedListDto;
import com.socialMeli.dto.response.MessageDto;
import com.socialMeli.dto.response.VendorFollowCountDto;
import com.socialMeli.entity.User;
import com.socialMeli.entity.UserType;
import com.socialMeli.exception.NotFoundException;
import com.socialMeli.exception.UserFollowException;
import com.socialMeli.exception.UserIsNotVendorException;
import com.socialMeli.repository.IUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.socialMeli.entity.UserType.CLIENT;
import static com.socialMeli.entity.UserType.VENDOR;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
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
        vendorUser = new User(1, "agustin", List.of(2, 3), List.of(2), VENDOR);
        clientUser = new User(2, "diego", List.of(), List.of(1, 3), CLIENT);
        when(userRepository.findUserByUserId(anyInt())).thenReturn(Optional.of(vendorUser));
    }

    @Test
    void getFollowerCountOkTest() {
        VendorFollowCountDto expectedVendorFollowerCount = new VendorFollowCountDto(vendorUser);
        VendorFollowCountDto vendorFollowCountDto = userService.getFollowerCount(1);
        assertEquals(expectedVendorFollowerCount, vendorFollowCountDto);
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

    @Test
    void unfollowUserOkTest() {
        MessageDto expectedMessageDto = new MessageDto("Dejaste de seguir a diego");
        when(userRepository.findUserByUserId(2)).thenReturn(Optional.of(clientUser));
        MessageDto actualMessageDto = userService.unfollowUser(1, 2);
        verify(userRepository).unfollowUser(any(User.class), any(User.class));
        assertEquals(expectedMessageDto, actualMessageDto);
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2})
    void unfollowNotFoundUserTest(Integer userId) {
        when(userRepository.findUserByUserId(userId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userService.unfollowUser(1, 2));
    }
    @Test
    void unfollowUserIsNotInFollowersListTest() {
        vendorUser = new User(1, "agustin", List.of(2, 3), List.of(), VENDOR);
        when(userRepository.findUserByUserId(1)).thenReturn(Optional.of(vendorUser));
        assertThrows(UserFollowException.class, () -> userService.unfollowUser(1, 2));
    }
    @Test
    void getFollowedListNoOrderTest() {
        Integer userId = 1;
        String order = null;
        FollowedListDto followedListDto = userService.getFollowedList(userId, null);

        Assertions.assertNotNull(followedListDto);
        Assertions.assertEquals(userId, followedListDto.getUserId());

    }


}