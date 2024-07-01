package org.wildcodeschool.blog.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @PrePersist
    public void create(){
        final LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void update(){
        this.updatedAt = LocalDateTime.now();
    }
}


