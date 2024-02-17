package com.socialMeli.service;

import com.socialMeli.repository.IPostRepository;
import com.socialMeli.repository.IProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostService implements IPostService {
    private final IPostRepository postRepository;
    private final IProductRepository productRepository;

}
