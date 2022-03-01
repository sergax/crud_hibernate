package com.sergax.crudhibernate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tag_post")
public class TagPost {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @JoinTable(name = "tag" , joinColumns = @JoinColumn(name = "tag_id"))
    private Long tag_id;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @JoinTable(name = "post" , joinColumns = @JoinColumn(name = "post_id"))
    private Long post_id;
}
