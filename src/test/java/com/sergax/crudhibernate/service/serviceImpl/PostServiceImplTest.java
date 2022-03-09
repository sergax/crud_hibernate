package com.sergax.crudhibernate.service.serviceImpl;

import com.sergax.crudhibernate.model.Post;
import com.sergax.crudhibernate.model.PostStatus;
import com.sergax.crudhibernate.model.Tag;
import com.sergax.crudhibernate.repository.hibernateRepoImpl.PostRepoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PostServiceImplTest {
    @InjectMocks
    private PostServiceImpl postService = new PostServiceImpl();

    @Mock
    private PostRepoImpl postRepo;

    @Mock
    private List<Post> postList;

    @Mock
    private Post post;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getById() {
        when(postRepo.getById(3L)).thenReturn(post);

        Post postTest = new Post(3L, "Content", PostStatus.ACTIVE);
        assertEquals(postTest.getPost_id(), postService.getById(3L).getPost_id());
    }

    @Test
    void getAll() {
        when(postRepo.getAll()).thenReturn(postList);
    }

    @Test
    void deleteById() {
        when(postRepo.deleteById(2L)).thenReturn(true);
    }

    @Test
    void create() {
        when(postRepo.create(any())).thenReturn(post);
        when(post.getPost_id()).thenReturn(2L);

        Post postTest = new Post(2L, "Tag", PostStatus.ACTIVE);
        assertEquals(postTest.getPost_id(), postRepo.create(post).getPost_id());
    }

    @Test
    void update() {
        when(postRepo.update(any())).thenReturn(post);
        when(post.getPost_id()).thenReturn(2L);

        Post postTest = new Post(2L, "Tag", PostStatus.ACTIVE);
        assertEquals(postTest.getPost_id(), postRepo.update(post).getPost_id());
    }
}