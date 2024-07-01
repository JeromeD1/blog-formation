package org.wildcodeschool.blog.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wildcodeschool.blog.model.Article;
import org.wildcodeschool.blog.repository.ArticleRepository;

import java.util.List;

@RestController
@RequestMapping("/article")
@AllArgsConstructor
public class ArticleController {
    private ArticleRepository articleRepository;

    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable long id) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(article);
    }

    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        Article savedArticle = articleRepository.save(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@RequestBody Article articleDetails, @PathVariable long id) {
        Article articleToUpdate = articleRepository.findById(id).orElse(null);
        if (articleToUpdate == null) {
            return ResponseEntity.notFound().build();
        } else {
            articleToUpdate.setTitle(articleDetails.getTitle());
            articleToUpdate.setContent(articleDetails.getContent());
            Article savedArticle = articleRepository.save(articleToUpdate);
            return ResponseEntity.ok(savedArticle);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Article> deleteArticle(@PathVariable long id) {
        Article articleToDelete = articleRepository.findById(id).orElse(null);
        if (articleToDelete == null) {
            return ResponseEntity.notFound().build();
        }
        articleRepository.delete(articleToDelete);
        return ResponseEntity.noContent().build();
    }
}
