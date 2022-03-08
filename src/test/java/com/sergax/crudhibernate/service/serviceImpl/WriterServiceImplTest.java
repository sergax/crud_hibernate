package com.sergax.crudhibernate.service.serviceImpl;

import com.sergax.crudhibernate.model.Post;
import com.sergax.crudhibernate.model.Writer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WriterServiceImplTest {
    private WriterServiceImpl writerService;
    private List<Writer> writerList;

    @BeforeEach
    void setUp() {
        this.writerService = mock(WriterServiceImpl.class);
    }

    @Test
    void getById() {
        Writer writer = new Writer();
        List<Post> postList = new ArrayList<>();
        writer.setId(1L);
        writer.setName("Writer");
        writer.setPostList(postList);

        when(writerService.getById(1L)).thenReturn(writer);
    }

    @Test
    void getAll() {
        when(writerService.getAll()).thenReturn(writerList);
    }

    @Test
    void deleteById() {
        when(writerService.deleteById(5L)).thenReturn(true);
        assertTrue(writerService.deleteById(5L));
    }

    @Test
    void create() {
        Writer writer = new Writer();
        List<Post> postList = new ArrayList<>();
        writer.setId(7L);
        writer.setName("Writer");
        writer.setPostList(postList);

        when(writerService.create(writer)).thenReturn(writer);
        Writer writer1 = writerService.create(writer);
        assertEquals(writerService.create(writer), writer1);
    }

    @Test
    void update() {
        Writer writer = new Writer();
        List<Post> postList = new ArrayList<>();
        writer.setId(7L);
        writer.setName("Writer");
        writer.setPostList(postList);

        when(writerService.create(writer)).thenReturn(writer);
        assertEquals(writerService.create(writer), writer);
    }
}