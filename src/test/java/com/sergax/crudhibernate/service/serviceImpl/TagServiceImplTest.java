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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TagServiceImplTest {

    @InjectMocks
    TagServiceImpl tagService = new TagServiceImpl();

    @Mock
    private TagRepoImpl tagRepo;

    private Tag tag1 = new Tag(1L, "Test");

    //openMocks(this) call tells Mockito to scan this test class instance for any fields
    // annotated with the @Mock annotation and initialize those fields as mocks.
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getById() {
        when(tagRepo.getById(anyLong())).thenReturn(mock(Tag.class));
        when(tagRepo.getById(2L)).thenReturn(tag1);

        Tag tagTest = new Tag(2L, "TestName");
        assertEquals(tagTest.getTag_id(), tagService.getById(1L).getTag_id());
    }

    @Test
    void getAll() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }
}