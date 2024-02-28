package com.socialMeli.repository;

import com.socialMeli.entity.User;
import com.socialMeli.entity.UserType;
import com.socialMeli.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class UserRepositoryTest {

    UserRepository userRepository;

    User vendor;
    User client1;
    User client2;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
        //vendor = new User(1,"luis",List.of(2,3),List.of(),UserType.VENDOR);
        client1 = new User(1,"Luciano Gonzalez",List.of(),List.of(8,10),UserType.CLIENT);
        client2 = new User(2,"Sofia Fernandez",List.of(),List.of(8),UserType.CLIENT);
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


    //T-0003 y T-0004 -> US-0003
    @Test
    void getAllFollowersFoundTest() {
        List<User> expectedUsersList = List.of(client1, client2);
        List<User> actualUsers = userRepository.getAllFollowers(List.of(1,2));
        assertEquals(expectedUsersList, actualUsers);
    }
    @Test
    void getAllFollowersNotFoundTest() {
        assertThrows(NotFoundException.class, () -> userRepository.getAllFollowers(List.of(1,-2)));
    }
}