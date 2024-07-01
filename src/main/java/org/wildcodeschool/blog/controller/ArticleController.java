package org.wildcodeschool.blog.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wildcodeschool.blog.mapper.ArticleMapper;
import org.wildcodeschool.blog.model.DTO.ArticleDTO;
import org.wildcodeschool.blog.model.entity.Article;
import org.wildcodeschool.blog.model.entity.Category;
import org.wildcodeschool.blog.repository.ArticleRepository;
import org.wildcodeschool.blog.repository.CategoryRepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/article")
@AllArgsConstructor
public class ArticleController {
    private ArticleRepository articleRepository;
    private CategoryRepository categoryRepository;
    private ArticleMapper articleMapper;


    @GetMapping
    public ResponseEntity<List<ArticleDTO>> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<ArticleDTO> articlesDTO = articles.stream().map(articleMapper::convertToDTO).toList();
        return ResponseEntity.ok(articlesDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable long id) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(articleMapper.convertToDTO(article));
    }

    @PostMapping
    public ResponseEntity<ArticleDTO> createArticle(@RequestBody Article article) {
        if(article.getCategory() != null) {
            Category category = categoryRepository.findById(article.getCategory().getId()).orElse(null);
            if (category == null) {
                return ResponseEntity.badRequest().body(null);
            } else {
                article.setCategory(category);
            }
        }
        Article savedArticle = articleRepository.save(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(articleMapper.convertToDTO(savedArticle));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleDTO> updateArticle(@RequestBody Article articleDetails, @PathVariable long id) {
        Article articleToUpdate = articleRepository.findById(id).orElse(null);
        if (articleToUpdate == null) {
            return ResponseEntity.notFound().build();
        } else {
            articleToUpdate.setTitle(articleDetails.getTitle());
            articleToUpdate.setContent(articleDetails.getContent());

            // Mise à jour de la catégorie
            if (articleDetails.getCategory() != null) {
                Category category = categoryRepository.findById(articleDetails.getCategory().getId()).orElse(null);
                if (category == null) {
                    return ResponseEntity.badRequest().body(null); // Retourne une réponse 400 Bad Request si la catégorie n'est pas trouvée
                }
                articleToUpdate.setCategory(category);
            }
            Article savedArticle = articleRepository.save(articleToUpdate);
            return ResponseEntity.ok(articleMapper.convertToDTO(savedArticle));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
        Article articleToDelete = articleRepository.findById(id).orElse(null);
        if (articleToDelete == null) {
            return ResponseEntity.notFound().build();
        }
        articleRepository.delete(articleToDelete);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<ArticleDTO>> getArticleByTitle(@PathVariable String title) {
        List<Article> articles = articleRepository.findByTitle(title);
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<ArticleDTO> articlesDTO = articles.stream().map(articleMapper::convertToDTO).toList();
        return ResponseEntity.ok(articlesDTO);
    }

    @GetMapping("/content/contain/{content}")
    public ResponseEntity<List<ArticleDTO>> getArticleByContent(@PathVariable String content) {
        List<Article> articles = articleRepository.findByContentContaining(content);
        if(articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<ArticleDTO> articlesDTO = articles.stream().map(articleMapper::convertToDTO).toList();
        return ResponseEntity.ok(articlesDTO);
    }

    @GetMapping("/createdAfter/{date}")
    public ResponseEntity<List<ArticleDTO>> getArticleByCreatedAfter(@PathVariable String date) {
        LocalDateTime limitDate = LocalDateTime.parse(date);
        List<Article> articles = articleRepository.findByCreatedAtAfter(limitDate);
        if(articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<ArticleDTO> articlesDTO = articles.stream().map(articleMapper::convertToDTO).toList();
        return ResponseEntity.ok(articlesDTO);
    }

    @GetMapping("/last")
    public ResponseEntity<List<ArticleDTO>> getArticleByLast() {
        Page<Article> articles = articleRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(0,5));
        if(articles.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        List<ArticleDTO> articlesDTO = articles.stream().map(articleMapper::convertToDTO).toList();
        return ResponseEntity.ok(articlesDTO);
    }
}
