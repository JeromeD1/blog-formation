package org.wildcodeschool.blog.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.wildcodeschool.blog.model.DTO.TagDTO;
import org.wildcodeschool.blog.model.entity.Article;
import org.wildcodeschool.blog.model.entity.Tag;
import org.wildcodeschool.blog.repository.ArticleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class TagMapper {

    private ArticleRepository articleRepository;

    public TagDTO convertToDTO(Tag tag) {
        TagDTO tagDTO = new TagDTO();
        tagDTO.setId(tag.getId());
        tagDTO.setName(tag.getName());
        if (tag.getArticles() != null) {
            tagDTO.setArticleIds(tag.getArticles().stream().map(Article::getId).collect(Collectors.toList()));
        }
        return tagDTO;
    }

    public Tag convertToEntity(TagDTO tagDTO) {
        Tag tag = new Tag();
        tag.setId(tagDTO.getId());
        tag.setName(tagDTO.getName());
        if (tagDTO.getArticleIds() != null) {
            List<Article> articles = articleRepository.findAllById(tagDTO.getArticleIds());
            tag.setArticles(articles);
        }
        return tag;
    }
}
