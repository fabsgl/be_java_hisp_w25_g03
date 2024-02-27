package com.socialMeli.repository;

import com.socialMeli.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UserRepositoryTest {

    UserRepository userRepository;
    User userVendor;
    User userClient;

    static Stream<Arguments> userIds() {
        return Stream.of(
                Arguments.arguments(1, 10),
                Arguments.arguments(1, 9));
    }

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
        userClient = userRepository.userBd.get(0);
        userVendor = userRepository.userBd.get(9);

    }

    @Test
    void findUserByUserIdFoundTest() {
        Optional<User> user = userRepository.findUserByUserId(1);
        Assertions.assertTrue(user.isPresent());
    }

    @Test
    void findUserByUserIdNotFoundTest() {
        Optional<User> user = userRepository.findUserByUserId(-1);
        Assertions.assertTrue(user.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("userIds")
    void unfollowUserWhoDontFollowEachOtherTest(int userId, int userToUnfollowId) {
        int userPos = userId - 1;
        int userToUnfollowPos = userToUnfollowId - 1;
        userRepository.unfollowUser(userRepository.userBd.get(userPos), userRepository.userBd.get(userToUnfollowPos));
        assertTrue(userRepository.userBd.get(userPos)
                .getFollowedId().stream().noneMatch(id -> id.equals(userToUnfollowId)));
        assertTrue(userRepository.userBd.get(userToUnfollowPos)
                .getFollowersId().stream().noneMatch(id -> id.equals(userId)));
    }

}