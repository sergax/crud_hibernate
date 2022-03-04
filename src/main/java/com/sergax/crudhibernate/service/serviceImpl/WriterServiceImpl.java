package com.sergax.crudhibernate.service.serviceImpl;

import com.sergax.crudhibernate.model.Writer;
import com.sergax.crudhibernate.repository.hibernateRepoImpl.WriterRepoImpl;
import com.sergax.crudhibernate.repository.WriterRepository;
import com.sergax.crudhibernate.service.WriterService;

import java.util.List;

public class WriterServiceImpl implements WriterService {
    private final WriterRepository writerRepository = new WriterRepoImpl();


    @Override
    public Writer getById(Long id) {
        return writerRepository.getById(id);
    }

    @Override
    public List getAll() {
        return writerRepository.getAll();
    }

    @Override
    public void deleteById(Long id) {
        writerRepository.deleteById(id);
    }

    @Override
    public Writer create(Writer writer) {
        return writerRepository.create(writer);
    }

    @Override
    public Writer update(Writer writer) {
        return writerRepository.update(writer);
    }
}
