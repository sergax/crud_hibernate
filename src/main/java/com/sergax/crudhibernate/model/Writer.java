package com.sergax.crudhibernate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "writer")
public class Writer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "writer_id")
    private Long writer_id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "post")
    @JoinColumn(name = "post_id")
    private List<Post> postList;

    @Override
    public String toString() {
        return "Writer : \n " +
                " writer_id : " + writer_id + " |" +
                " name : " + name + " |" +
                " postList : " + postList + "\n";
    }
}
