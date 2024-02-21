package com.socialMeli.repository;

import com.socialMeli.entity.Post;
import com.socialMeli.entity.Product;
import com.socialMeli.entity.PromotionalPost;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository implements IPostRepository {
    List<Product> productBd;
    List<Post> postBd;

    public PostRepository() {
        this.productBd = new ArrayList<>();
        this.postBd = new ArrayList<>();
    }

    @Override
    public Integer add(Post post) {
        this.postBd.add(post);
        return post.getId();
    }
    Period twoWeeksPeriod = Period.ofWeeks(2);

    @Override
    public Optional<List<Post>> getPostFromTheLastTwoWeeksByUserId(Integer userId) {
        List<Post> filteredPost = postBd.stream().filter(
                post -> post.getUserId().equals(userId) &&
                        post.getDate().isAfter(LocalDate.now().minus(twoWeeksPeriod))
        ).toList();
        return filteredPost.isEmpty() ? Optional.empty() : Optional.of(filteredPost);
    }

    @Override
    public Optional<List<Post>> getPromotedPostByUserId(Integer userId) {
        List<Post> filteredPost = postBd.stream().filter(
                post -> post.getUserId().equals(userId) &&
                        post instanceof PromotionalPost
        ).toList();
        return filteredPost.isEmpty() ? Optional.empty() : Optional.of(filteredPost);
    }
}
