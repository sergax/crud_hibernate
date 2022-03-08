package com.sergax.crudhibernate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long tag_id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tag_post", joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id"))
    private List<Post> postList;

    public Tag(Long tag_id, String name) {
        this.tag_id = tag_id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tag :" +
                " tag_id : " + tag_id + " |" +
                " name : " + name + "\n";
    }
}
