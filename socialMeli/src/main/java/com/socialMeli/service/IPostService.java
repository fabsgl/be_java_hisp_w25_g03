package com.socialMeli.service;

import com.socialMeli.dto.request.PostDTO;
import com.socialMeli.entity.Post;

public interface IPostService {

    void addPost(PostDTO post);
}
