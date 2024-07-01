package org.wildcodeschool.blog.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.wildcodeschool.blog.model.DTO.ArticleDTO;
import org.wildcodeschool.blog.model.entity.Article;
import org.wildcodeschool.blog.model.entity.Category;
import org.wildcodeschool.blog.repository.CategoryRepository;

@Component
@AllArgsConstructor
public class ArticleMapper {
    private CategoryRepository categoryRepository;

    public ArticleDTO convertToDTO(Article article) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(article.getId());
        articleDTO.setTitle(article.getTitle());
        articleDTO.setContent(article.getContent());
        articleDTO.setCreatedAt(article.getCreatedAt());
        articleDTO.setUpdatedAt(article.getUpdatedAt());
        if (article.getCategory() != null) {
            articleDTO.setCategoryId(article.getCategory().getId());
        }
        return articleDTO;
    }

    public Article convertToEntity(ArticleDTO articleDTO) {
        Article article = new Article();
        article.setId(articleDTO.getId());
        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());
        article.setCreatedAt(articleDTO.getCreatedAt());
        article.setUpdatedAt(articleDTO.getUpdatedAt());
        if (articleDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(articleDTO.getCategoryId()).orElse(null);
            article.setCategory(category);
        }
        return article;
    }
}
