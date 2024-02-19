package com.socialMeli.service;

import com.socialMeli.dto.request.PostDTO;
import com.socialMeli.entity.Post;
import com.socialMeli.exception.InvalidDataException;
import com.socialMeli.repository.IPostRepository;
import com.socialMeli.repository.IProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

@Service
@AllArgsConstructor
public class PostService implements IPostService {
    private final IPostRepository postRepository;
    private final IProductRepository productRepository;

    @Override
    public void addPost(PostDTO post) {
        try {
            Post finalPost = new Post(post.getUser_id(), post.getDate(), post.getProduct(), post.getCategory(), post.getPrice());
            productRepository.add(post.getProduct());
            postRepository.add(finalPost);
        }
        catch (IllegalArgumentException e) {
            throw new InvalidDataException("Error al enviar los datos: Argumento inválido");
        }
    }
}
