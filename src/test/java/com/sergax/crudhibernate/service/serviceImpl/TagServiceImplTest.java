package com.sergax.crudhibernate.service.serviceImpl;

import com.sergax.crudhibernate.model.Tag;
import com.sergax.crudhibernate.repository.hibernateRepoImpl.TagRepoImpl;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TagServiceImplTest {
    @InjectMocks
    private TagServiceImpl tagService;

    @Mock
    private TagRepoImpl tagRepo;

    @Mock
    private Tag tag;
    private Tag tagTest = new Tag(10L, "NameTest");

    @Mock
    private List<Tag> tagList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getById() {
        when(tagRepo.getById(11L)).thenReturn(tag);

        tag = tagService.getById(10L);
        assertNotNull(tag);
        assertNotNull(tag.getTag_id());
        assertNotNull(tag.getName());
        assertEquals(10L, tagTest.getTag_id());
        assertEquals("NameTest", tagTest.getName());

        assertEquals(tagTest.getTag_id(), tagService.getById(10L).getTag_id());
    }

    @Test
    void getAll() {
        when(tagRepo.getAll()).thenReturn(tagList);
        assertNotNull(tagList);
    }

    @Test
    void deleteById() {
        when(tagRepo.deleteById(6L)).thenReturn(true);
    }

    @Test
    void create() {
        when(tagRepo.create(any())).thenReturn(tag);
        when(tag.getTag_id()).thenReturn(11L);

        tag = tagService.create(tagTest);
        assertNotNull(tag);
        assertNotNull(tag.getTag_id());
        assertNotNull(tag.getName());
        assertEquals(10L, tagTest.getTag_id());
        assertEquals("NameTest", tagTest.getName());

        assertEquals(tagTest.getTag_id(), tagService.create(tag).getTag_id());
    }

    @Test
    void update() {
        when(tagRepo.update(any())).thenReturn(tag);
        when(tag.getTag_id()).thenReturn(10L);

        Tag updatedTag = new Tag(tag.getTag_id(), "Updated_Name");
        tag = tagService.update(updatedTag);
        assertNotNull(tag);
        assertNotNull(tag.getTag_id());
        assertNotNull(tag.getName());
        assertEquals(tag.getName(), updatedTag.getName());
        assertEquals(tag.getTag_id(), updatedTag.getTag_id());

        assertEquals(tagTest.getTag_id(), tagService.update(tag).getTag_id());
    }
}