package com.sergax.crudhibernate.controller;

import com.sergax.crudhibernate.model.Tag;
import com.sergax.crudhibernate.service.TagService;
import com.sergax.crudhibernate.service.serviceImpl.TagServiceImpl;

import java.util.List;

public class TagController {
    private final TagService tagService = new TagServiceImpl();

    public Tag getById(Long id) {
        return tagService.getById(id);
    }

    public List<Tag> getAll() {
        return tagService.getAll();
    }

    public void deleteById(Tag tag) {
        tagService.deleteById(tag);
    }

    public Tag create(Tag tag) {
        return tagService.create(tag);
    }

    public Tag update(Tag tag) {
      return tagService.update(tag);
    }
}
