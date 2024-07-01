package org.wildcodeschool.blog.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wildcodeschool.blog.model.Article;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByTitle(String title);

    List<Article> findByContentContaining(String contentPart);

    List<Article> findByCreatedAtAfter(LocalDateTime createdAt);

    Page<Article> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
