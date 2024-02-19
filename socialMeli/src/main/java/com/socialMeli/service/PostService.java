package com.socialMeli.service;

import com.socialMeli.dto.response.PostDto;
import com.socialMeli.dto.response.PublicationDto;
import com.socialMeli.entity.Post;
import com.socialMeli.exception.NotFoundException;
import com.socialMeli.repository.IPostRepository;
import com.socialMeli.repository.IProductRepository;
import com.socialMeli.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService implements IPostService {
    private final IPostRepository postRepository;
    private final IProductRepository productRepository;
    private final IUserRepository userRepository;

    @Override
    public PublicationDto obtainLastPublicationsByTheFollowedVendors(Integer userId) {
        List<Integer> followedVendors = userRepository.findUserByUserId(userId)
                .orElseThrow(() -> new NotFoundException("No se encontro al usuario"))
                .getFollowedId();
        List<Post> latestPost = new ArrayList<>();
        for (Integer vendorId : followedVendors) {
            Optional<List<Post>> filteredPostOfOneUser = postRepository.getPostFromTheLastTwoWeeksByUserId(vendorId);
            filteredPostOfOneUser.ifPresent(latestPost::addAll);
        }

        List<PostDto> sortedPost = latestPost.stream()
                .map(this::convertPostToDto)
                .toList();

        return new PublicationDto(userId, sortPostsByDate(sortedPost));
    }

    public List<PostDto> sortPostsByDate(List<PostDto> posts) {
        return posts.stream()
                .sorted(Comparator.comparing(PostDto::getDate).reversed())
                .collect(Collectors.toList());
    }

    public PostDto convertPostToDto(Post post) {
        PostDto convertedPost = new PostDto();
        convertedPost.setUser_id(post.getUserId());
        convertedPost.setId(post.getId());
        convertedPost.setProduct(post.getProduct());
        convertedPost.setDate(post.getDate());
        convertedPost.setPrice(post.getPrice());
        convertedPost.setCategory(post.getCategory());
        return convertedPost;
    }
}
