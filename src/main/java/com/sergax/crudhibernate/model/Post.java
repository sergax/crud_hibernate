package com.sergax.crudhibernate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long post_id;

    @Column(name = "content")
    private String content;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tag_post", joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tagList;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "post_writer_id")
    private Writer writer;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PostStatus poststatus;

    @Override
    public String toString() {
        return "Post : " +
                " post_id : " + post_id + " |" +
                " content : " + content + " |" +
                " tagList : " + tagList + " |" +
                " status : " + poststatus + "\n";
    }
}
