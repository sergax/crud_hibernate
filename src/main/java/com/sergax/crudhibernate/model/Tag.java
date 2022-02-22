package com.sergax.crudhibernate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tag")
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
