package com.sergax.crudhibernate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    private Long tag_id;
    private String name;

    @Override
    public String toString() {
        return "Tag{" +
                "tag_id=" + tag_id +
                ", name='" + name + '\'' +
                '}';
    }
}
