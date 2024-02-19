package com.socialMeli.repository;

import com.socialMeli.entity.User;

import java.util.Optional;

public interface IUserRepository {
    Optional<User> findUserByUserId(Integer userId);
}
