package org.wildcodeschool.blog.model.DTO;

import lombok.Data;
import org.wildcodeschool.blog.model.entity.Article;

import java.util.List;

@Data
public class CategoryDTO {
    private Long id;
    private String name;
    private List<Article> articles;
}
