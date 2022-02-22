package com.sergax.crudhibernate.model;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tag_id")
    private Long tag_id;

    @Column(name = "name")
    private String name;

    @Override
    public String toString() {
        return "Tag :\n" +
                "tag_id :" + tag_id + "\n" +
                "name :'" + name + "\n";
    }
}
