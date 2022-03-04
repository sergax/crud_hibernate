package com.sergax.crudhibernate.service.serviceImpl;

import com.sergax.crudhibernate.model.Post;
import com.sergax.crudhibernate.repository.hibernateRepoImpl.PostRepoImpl;
import com.sergax.crudhibernate.repository.PostRepository;
import com.sergax.crudhibernate.service.PostService;

import java.util.List;

public class PostServiceImpl implements PostService {
    private final PostRepository postRepository = new PostRepoImpl();

    @Override
    public Post getById(Long id) {
        return postRepository.getById(id);
    }

    @Override
    public List<Post> getAll() {
        return postRepository.getAll();
    }

    @Override
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Post create(Post post) {
        return postRepository.create(post);
    }

    @Override
    public Post update(Post post) {
        return postRepository.update(post);
    }
}
