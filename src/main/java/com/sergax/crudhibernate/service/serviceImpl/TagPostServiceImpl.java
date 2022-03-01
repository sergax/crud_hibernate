package com.sergax.crudhibernate.service.serviceImpl;

import com.sergax.crudhibernate.model.TagPost;
import com.sergax.crudhibernate.repository.HibernateRepoImpl.TagPostRepoImpl;
import com.sergax.crudhibernate.repository.TagPostRepository;

import java.util.List;

public class TagPostServiceImpl {
    TagPostRepository tagPostRepository = new TagPostRepoImpl();

    public TagPost create(TagPost tagPost) {
        return tagPostRepository.create(tagPost);
    }

    public List<Long> getAll() { return tagPostRepository.getAll();}
}
