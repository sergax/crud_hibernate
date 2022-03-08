package com.sergax.crudhibernate.service.serviceImpl;

import com.sergax.crudhibernate.model.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TagServiceImplTest {
    private TagServiceImpl tagService;
    private List<Tag> tagList;

    @BeforeEach
    void setUp() {
        this.tagService = mock(TagServiceImpl.class);
    }

    @Test
    void getById() {
        Tag tag = new Tag();
        tag.setTag_id(2L);
        tag.setName("Tag");

        when(tagService.getById(2L)).thenReturn(tag);
    }

    @Test
    void getAll() {
        when(tagService.getAll()).thenReturn(tagList);
    }

    @Test
    void deleteById() {
        when(tagService.deleteById(1L)).thenReturn(true);
        assertTrue(tagService.deleteById(1L));
    }

    @Test
    void create() {
        Tag tag = new Tag();
        tag.setTag_id(3L);
        tag.setName("Tag");

        when(tagService.create(tag)).thenReturn(tag);
        Tag tag1 = tagService.create(tag);
        assertEquals(tagService.create(tag), tag1);
    }

    @Test
    void update() {
        Tag tag = new Tag();
        tag.setTag_id(5L);
        tag.setName("Tag1");

        when(tagService.update(tag)).thenReturn(tag);
        assertEquals(tagService.update(tag), tag);
    }
}