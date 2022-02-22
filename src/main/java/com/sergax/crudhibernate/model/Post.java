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
public class Post {
    private Long post_id;
    private String content;
    private List<Tag> tagList;
    private Poststatus poststatus;

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
