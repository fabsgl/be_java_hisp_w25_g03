package com.socialMeli.repository;

import com.socialMeli.entity.User;
import com.socialMeli.entity.UserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
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
}