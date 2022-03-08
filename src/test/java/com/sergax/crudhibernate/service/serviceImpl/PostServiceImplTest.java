package com.sergax.crudhibernate.service.serviceImpl;

import com.sergax.crudhibernate.model.Post;
import com.sergax.crudhibernate.model.PostStatus;
import com.sergax.crudhibernate.model.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PostServiceImplTest {
    private PostServiceImpl postService;
    private List<Post> postList;

    @BeforeEach
    void setUp() {
        this.postService = mock(PostServiceImpl.class);
    }

    @Test
    void getById() {
        Post post = new Post();
        List<Tag> tagList = new ArrayList<>();
        post.setPost_id(5L);
        post.setContent("Content");
        post.setTagList(tagList);
        post.setPoststatus(PostStatus.ACTIVE);

        when(postService.getById(5L)).thenReturn(post);
    }

    @Test
    void getAll() {
        when(postService.getAll()).thenReturn(postList);
    }

    @Test
    void deleteById() {
        when(postService.deleteById(2L)).thenReturn(true);
        assertTrue(postService.deleteById(2L));
    }

    @Test
    void create() {
        Post post = new Post();
        List<Tag> tagList = new ArrayList<>();
        post.setPost_id(6L);
        post.setContent("Content");
        post.setTagList(tagList);
        post.setPoststatus(PostStatus.ACTIVE);

        when(postService.create(post)).thenReturn(post);
        Post newPost = postService.create(post);
        assertEquals(postService.create(post), newPost);
    }

    @Test
    void update() {
        Post post = new Post();
        List<Tag> tagList = new ArrayList<>();
        post.setPost_id(7L);
        post.setContent("Content1");
        post.setTagList(tagList);
        post.setPoststatus(PostStatus.ACTIVE);

        when(postService.update(post)).thenReturn(post);
        assertEquals(postService.update(post), post);
    }
}