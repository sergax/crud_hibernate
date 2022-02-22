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


    private List<Tag> tagList;

    @Column(name = "status")
    private PostStatus poststatus;

    @Override
    public String toString() {
        return "Post{" +
                "post_id=" + post_id +
                ", content='" + content + '\'' +
                ", tagList=" + tagList +
                ", poststatus=" + poststatus +
                '}';
    }
}



