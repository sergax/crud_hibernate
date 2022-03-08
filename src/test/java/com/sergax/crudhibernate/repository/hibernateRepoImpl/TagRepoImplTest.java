package com.sergax.crudhibernate.repository.hibernateRepoImpl;

import com.sergax.crudhibernate.model.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class TagRepoImplTest {
    private TagRepoImpl tagRepository;

    @BeforeEach
    void setUp() {
        this.tagRepository = mock(TagRepoImpl.class);
    }

    @Test
    void getById() {
        Tag tag = new Tag();
        tag.setTag_id(2L);
        tag.setName("Tag");

        when(tagRepository.getById(2L)).thenReturn(tag);
        Tag tag1 = tagRepository.getById(2L);
        String name = tag1.getName();
        String name1 = "Tag";
        assertEquals(name, name1);
    }


    @Test
    void getAll() {
        when(tagRepository.getAll()).thenReturn(Collections.singletonList(Tag.class));
    }

    @Test
    void deleteById() {
        when(tagRepository.deleteById(1L)).thenReturn(true);
        assertTrue(tagRepository.deleteById(1L));
    }

    @Test
    void create() {
        Tag tag = new Tag();
        tag.setTag_id(3L);
        tag.setName("Tag");
        when(tagRepository.create(tag)).thenReturn(tag);
        Tag tag1 = tagRepository.create(tag);
        assertEquals(tagRepository.create(tag), tag1);
    }

    @Test
    void update() {
        Tag tag = new Tag();
        tag.setTag_id(5L);
        tag.setName("Tag1");
        when(tagRepository.update(tag)).thenReturn(tag);
        assertEquals(tagRepository.update(tag), tag);
    }
}
