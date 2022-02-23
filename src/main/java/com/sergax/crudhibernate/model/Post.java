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
@Table(name = "Post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private Long post_id;

    @Column(name = "content")
    private String content;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "tag_post")
    @JoinColumn(name = "post_id")
    private List<Tag> tagList;

    @Column(name = "status")
    private PostStatus poststatus;

    @Override
    public String toString() {
        return "Post : \n" +
                "post_id : " + post_id + "\n" +
                "content : " + content + "\n" +
                "tagList : " + tagList + "\n" +
                "status : " + poststatus;
    }
}



