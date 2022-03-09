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
    private TagServiceImpl tagService = new TagServiceImpl();

    @Mock
    private TagRepoImpl tagRepo;

    @Mock
    private Tag tag = new Tag();

    @Mock
    private List<Tag> tagList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getById() {
        when(tagRepo.getById(2L)).thenReturn(tag);

        Tag tagTest = new Tag(2L, "Tag");
        assertEquals(tagTest.getTag_id(), tagService.getById(2L).getTag_id());
    }

    @Test
    void getAll() {
        when(tagRepo.getAll()).thenReturn(tagList);
    }

    @Test
    void deleteById() {
        when(tagRepo.deleteById(1L)).thenReturn(true);
    }

    @Test
    void create() {
        when(tagRepo.create(any())).thenReturn(tag);
        when(tag.getTag_id()).thenReturn(2L);

        Tag tagTest = new Tag(2L, "Tag");
        assertEquals(tagTest.getTag_id(), tagRepo.create(tag).getTag_id());
    }

    @Test
    void update() {
        when(tagRepo.update(any())).thenReturn(tag);
        when(tag.getTag_id()).thenReturn(1L);

        Tag tagTest = new Tag(1L, "TagTest");
        assertEquals(tagTest.getTag_id(), tagRepo.update(tag).getTag_id());
    }
}