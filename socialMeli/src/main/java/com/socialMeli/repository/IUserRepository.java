package com.socialMeli.repository;

import com.socialMeli.dto.response.UserVendorDTO;
import com.socialMeli.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import com.socialMeli.entity.User;

import java.util.Optional;

public interface IUserRepository {

    Optional<User> findUserByUserId(Integer userId);
    List<User> getAllFollowers(List<Integer> followersIds);
}
