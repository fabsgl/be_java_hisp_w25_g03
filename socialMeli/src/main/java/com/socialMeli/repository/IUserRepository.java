package com.socialMeli.repository;

import com.socialMeli.entity.User;

import java.util.List;

public interface IUserRepository {
    User findUserByUserId(Integer userId);

    List<Integer> findFollowedVendorsByUserId(Integer userId);
}
