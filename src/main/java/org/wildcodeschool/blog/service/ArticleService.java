package org.wildcodeschool.blog.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.wildcodeschool.blog.mapper.ArticleMapper;
import org.wildcodeschool.blog.model.DTO.ArticleDTO;
import org.wildcodeschool.blog.model.entity.Article;
import org.wildcodeschool.blog.repository.ArticleRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    public List<ArticleDTO> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        return articles.stream().map(articleMapper::convertToDTO).collect(Collectors.toList());
    }

    public Optional<ArticleDTO> getArticleById(Long id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (!optionalArticle.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(articleMapper.convertToDTO(optionalArticle.get()));
    }

    public ArticleDTO createArticle(ArticleDTO articleDTO) {
        Article article = articleMapper.convertToEntity(articleDTO);
        article.setCreatedAt(LocalDateTime.now());
        article.setUpdatedAt(LocalDateTime.now());

        Article savedArticle = articleRepository.save(article);
        return articleMapper.convertToDTO(savedArticle);
    }

    public Optional<ArticleDTO> updateArticle(Long id, ArticleDTO articleDTO) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (!optionalArticle.isPresent()) {
            return Optional.empty();
        }
        Article article = optionalArticle.get();
        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());
        article.setUpdatedAt(LocalDateTime.now());

        if (articleDTO.getCategoryId() != null) {
            articleMapper.convertToEntity(articleDTO).getCategory();
        }

        Article updatedArticle = articleRepository.save(article);
        return Optional.of(articleMapper.convertToDTO(updatedArticle));
    }

    public boolean deleteArticle(Long id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (!optionalArticle.isPresent()) {
            return false;
        }
        articleRepository.delete(optionalArticle.get());
        return true;
    }

    public List<ArticleDTO> getArticleByTitle(String title) {
        List<Article> articles = articleRepository.findByTitle(title);
        return articles.stream().map(articleMapper::convertToDTO).toList();
    }

    public List<ArticleDTO> getArticleByContent(String content) {
        List<Article> articles = articleRepository.findByContentContaining(content);
        return articles.stream().map(articleMapper::convertToDTO).toList();
    }

    public List<ArticleDTO> getArticleByCreatedAfter(String date) {
        LocalDateTime limitDate = LocalDateTime.parse(date);
        List<Article> articles = articleRepository.findByCreatedAtAfter(limitDate);
        return articles.stream().map(articleMapper::convertToDTO).toList();
    }

    public List<ArticleDTO> getArticleByLast() {
        Page<Article> articles = articleRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(0,5));
        return articles.stream().map(articleMapper::convertToDTO).toList();
    }
}
