package com.sergax.crudhibernate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Writer {
    private Long writer_id;
    private String name;
    private List<Post> postList;

    @Override
    public String toString() {
        return "Writer{" +
                "writer_id=" + writer_id +
                ", name='" + name + '\'' +
                ", postList=" + postList +
                '}';
    }
}
