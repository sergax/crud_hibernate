package com.sergax.crudhibernate.controller;

import com.sergax.crudhibernate.model.Tag;
import com.sergax.crudhibernate.model.TagPost;
import com.sergax.crudhibernate.repository.HibernateRepoImpl.TagPostRepoImpl;
import com.sergax.crudhibernate.repository.TagPostRepository;
import com.sergax.crudhibernate.service.serviceImpl.TagPostServiceImpl;

import java.util.List;

public class TagPostController {
    TagPostServiceImpl tagPostService = new TagPostServiceImpl();

    public TagPost create(TagPost tagPost) {
        return tagPostService.create(tagPost);
    }

    public List<Long> getAll() {
        return tagPostService.getAll();
    }

}
