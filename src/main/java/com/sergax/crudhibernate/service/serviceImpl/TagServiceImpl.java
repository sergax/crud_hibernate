package com.sergax.crudhibernate.service.serviceImpl;

import com.sergax.crudhibernate.model.Tag;
import com.sergax.crudhibernate.repository.HibernateRepoImpl.TagRepoImpl;
import com.sergax.crudhibernate.repository.TagRepository;
import com.sergax.crudhibernate.service.TagService;

import java.util.List;

public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository = new TagRepoImpl();

    @Override
    public Tag getById(Long id) {
        return tagRepository.getById(id);
    }

    @Override
    public List<Tag> getAll() {
        return tagRepository.getAll();
    }

    @Override
    public void deleteById(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    public Tag create(Tag tag) {
        return tagRepository.create(tag);
    }

    @Override
    public Tag update(Tag tag) {
        return tagRepository.update(tag);
    }
}
