package com.sergax.crudhibernate.repository;

import com.sergax.crudhibernate.model.TagPost;

import java.util.List;

public interface TagPostRepository {

    TagPost create(TagPost tagPost);

    List<Long> getAll();
}
