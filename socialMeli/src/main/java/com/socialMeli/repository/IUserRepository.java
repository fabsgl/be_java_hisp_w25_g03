package com.socialMeli.repository;

import com.socialMeli.entity.User;

public interface IUserRepository {
    User findUserByUserId(Integer userId);
    User findUserToFollowById(Integer userIdToFollow);
}
