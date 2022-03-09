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
    private Post testPost = new Post(8L, "Content", PostStatus.ACTIVE);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getById() {
        when(postRepo.getById(8L)).thenReturn(post);

        post = postService.getById(8L);
        assertNotNull(post);
        assertNotNull(post.getPost_id());
        assertNotNull(post.getContent());
        assertEquals(8L, testPost.getPost_id());
        assertEquals("Content", testPost.getContent());
        assertEquals(PostStatus.ACTIVE, testPost.getPoststatus());

        assertEquals(testPost.getPost_id(), postService.getById(8L).getPost_id());
    }

    @Test
    void getAll() {
        when(postRepo.getAll()).thenReturn(postList);
        assertNotNull(postList);
    }

    @Test
    void deleteById() {
        when(postRepo.deleteById(2L)).thenReturn(true);
    }

    @Test
    void create() {
        when(postRepo.create(any())).thenReturn(post);
        when(post.getPost_id()).thenReturn(8L);

        post = postService.create(testPost);
        assertNotNull(post);
        assertNotNull(post.getPost_id());
        assertNotNull(post.getContent());
        assertEquals(8L, testPost.getPost_id());
        assertEquals("Content", testPost.getContent());
        assertEquals(PostStatus.ACTIVE, testPost.getPoststatus());

        assertEquals(testPost.getPost_id(), postService.create(post).getPost_id());
    }

    @Test
    void update() {
        when(postRepo.update(any())).thenReturn(post);
        when(post.getPost_id()).thenReturn(8L);

        Post updatedPost = new Post(post.getPost_id(), "Content_Updated", PostStatus.DELETE);
        post = postService.update(updatedPost);
        assertNotNull(post);
        assertNotNull(post.getPost_id());
        assertNotNull(post.getContent());
        assertEquals(post.getPost_id(), updatedPost.getPost_id());
        assertEquals(post.getContent(), updatedPost.getContent());
        assertEquals(post.getPoststatus(), updatedPost.getPoststatus());

        assertEquals(testPost.getPost_id(), postService.update(post).getPost_id());
    }
}