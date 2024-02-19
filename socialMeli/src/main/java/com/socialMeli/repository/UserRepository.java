package com.socialMeli.repository;

import com.socialMeli.dto.response.UserVendorDTO;
import com.socialMeli.entity.User;
import com.socialMeli.exception.NotFoundException;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepository implements IUserRepository{

    List<User> userBd;

    public UserRepository() {
        this.userBd = new ArrayList<>();
    }

    @Override
    public Optional<User> findUserByUserId(Integer userId) {
        return userBd.stream().filter(user -> Objects.equals(user.getId(),userId)).findFirst();
    }

    @Override
    public List<User> getAllFollowers(List<Integer> followersIds) {
        return followersIds.stream()
                .map(id -> findUserByUserId(id)
                        .orElseThrow(() -> new NotFoundException("Seguidor con id " + id + " no encontrado")))
                .toList();
        //todo Hay forma de hacerlo con streams sin excepciones porque doy por hecho que existe el Id?
    }
}
