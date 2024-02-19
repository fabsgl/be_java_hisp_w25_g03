package com.socialMeli.repository;

import com.socialMeli.entity.Post;
import com.socialMeli.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository

public class PostRepository implements IPostRepository{
    List<Product> productBd;
    List<Post> postBd;

    public PostRepository() {
        this.productBd = new ArrayList<>();
        this.postBd = new ArrayList<>();
    }

    @Override
    public void add(Post post) {
        this.postBd.add(post);
    }
}
