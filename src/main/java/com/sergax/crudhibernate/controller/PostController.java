package com.sergax.crudhibernate.controller;

import com.sergax.crudhibernate.model.Post;
import com.sergax.crudhibernate.service.PostService;
import com.sergax.crudhibernate.service.serviceImpl.PostServiceImpl;

import java.util.List;

public class PostController {
    private final PostService postService = new PostServiceImpl();

    public Post getById(Long id) {
        return postService.getById(id);
    }

    public List<Post> getAll() {
        return postService.getAll();
    }

    public void deleteById(Long id) {
        postService.deleteById(id);
    }

    public Post create(Post post) {
        return postService.create(post);
    }

    public Post update(Post post) {
        return postService.update(post);
    }
}
