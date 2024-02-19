package com.socialMeli.repository;

import com.socialMeli.entity.Post;

import java.util.List;
import java.util.Optional;

public interface IPostRepository {
    Optional<List<Post>> getPostFromTheLastTwoWeeksByUserId(Integer userId);
}
