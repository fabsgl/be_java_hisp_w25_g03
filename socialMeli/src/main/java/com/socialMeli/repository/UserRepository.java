package com.socialMeli.repository;

import com.socialMeli.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository{

    private List<User> userBd;

    public UserRepository() {
        this.userBd = new ArrayList<>();
    }

    @Override
    public Optional<User> findUserByUserId(Integer userId) {
        return userBd.stream().filter(user -> user.getId().equals(userId)).findFirst();
    }
}
