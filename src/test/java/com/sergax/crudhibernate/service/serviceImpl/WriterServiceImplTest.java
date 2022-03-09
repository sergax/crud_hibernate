package com.sergax.crudhibernate.service.serviceImpl;

import com.sergax.crudhibernate.model.Post;
import com.sergax.crudhibernate.model.PostStatus;
import com.sergax.crudhibernate.model.Writer;
import com.sergax.crudhibernate.repository.WriterRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class WriterServiceImplTest {

    @InjectMocks
    private WriterServiceImpl writerService;

    @Mock
    private List<Writer> writerList;

    @Mock
    private WriterRepository writerRepo;

    @Mock
    private Writer writer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getById() {
        when(writerRepo.getById(1L)).thenReturn(writer);

        Writer writerTest = new Writer(1L, "Name");
        assertEquals(writerTest.getId(), writerService.getById(1L).getId());
    }

    @Test
    void getAll() {
        when(writerRepo.getAll()).thenReturn(writerList);
    }

    @Test
    void deleteById() {
        when(writerRepo.deleteById(5L)).thenReturn(true);
    }

    @Test
    void create() {
        when(writerRepo.create(any())).thenReturn(writer);
        when(writer.getId()).thenReturn(2L);

        Writer writerTest = new Writer(2L, "Tag");
        assertEquals(writerTest.getId(), writerRepo.create(writer).getId());
    }

    @Test
    void update() {
        when(writerRepo.update(any())).thenReturn(writer);
        when(writer.getId()).thenReturn(2L);

        Writer writerTest = new Writer(2L, "Tag");
        assertEquals(writerTest.getId(), writerRepo.update(writer).getId());
    }
}