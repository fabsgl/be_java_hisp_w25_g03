package com.socialMeli.repository;

import com.socialMeli.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository implements IUserRepository{

    List<User> userBd;

    public UserRepository() {
        this.userBd = new ArrayList<>();
    }
}
