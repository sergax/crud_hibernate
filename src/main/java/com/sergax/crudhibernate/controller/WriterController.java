package com.sergax.crudhibernate.controller;

import com.sergax.crudhibernate.model.Writer;
import com.sergax.crudhibernate.service.WriterService;
import com.sergax.crudhibernate.service.serviceImpl.WriterServiceImpl;

import java.util.List;

public class WriterController {
    private final WriterService writerService = new WriterServiceImpl();

    public Writer getById(Long id) {
        return writerService.getById(id);
    }

    public List<Writer> getAll() {
        return writerService.getAll();
    }

    public void deleteById(Writer writer) {
        writerService.deleteById(writer);
    }

    public Writer create(Writer writer) {
        return writerService.create(writer);
    }

    public Writer update(Writer writer) {
        return writerService.update(writer);
    }
}
