package org.wildcodeschool.blog.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wildcodeschool.blog.model.DTO.ArticleDTO;
import org.wildcodeschool.blog.service.ArticleService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/article")
@AllArgsConstructor
public class ArticleController {

    private final ArticleService articleService;


    @GetMapping
    public ResponseEntity<List<ArticleDTO>> getAllArticles() {
        List<ArticleDTO> articles = articleService.getAllArticles();
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable long id) {
        Optional<ArticleDTO> articleDTO = articleService.getArticleById(id);
        if (!articleDTO.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(articleDTO.get());
    }

    @PostMapping
    public ResponseEntity<ArticleDTO> createArticle(@RequestBody ArticleDTO articleDTO) {
        ArticleDTO savedArticleDTO = articleService.createArticle(articleDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticleDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleDTO> updateArticle(@PathVariable Long id, @RequestBody ArticleDTO articleDTO) {
        Optional<ArticleDTO> updatedArticleDTO = articleService.updateArticle(id, articleDTO);
        if (!updatedArticleDTO.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedArticleDTO.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        if (articleService.deleteArticle(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<ArticleDTO>> getArticleByTitle(@PathVariable String title) {
        List<ArticleDTO> articles = articleService.getArticleByTitle(title);
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(articles);
        }
    }

    @GetMapping("/content/contain/{content}")
    public ResponseEntity<List<ArticleDTO>> getArticleByContent(@PathVariable String content) {
        List<ArticleDTO> articles = articleService.getArticleByContent(content);
        if(articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(articles);
        }
    }

    @GetMapping("/createdAfter/{date}")
    public ResponseEntity<List<ArticleDTO>> getArticleByCreatedAfter(@PathVariable String date) {
        List<ArticleDTO> articles = articleService.getArticleByCreatedAfter(date);
        if(articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(articles);
        }
    }

    @GetMapping("/last")
    public ResponseEntity<List<ArticleDTO>> getArticleByLast() {
        List<ArticleDTO> articles = articleService.getArticleByLast();
        if(articles.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(articles);
        }
    }
}
