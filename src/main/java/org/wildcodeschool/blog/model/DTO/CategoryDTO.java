package org.wildcodeschool.blog.model.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.wildcodeschool.blog.model.entity.Article;

import java.util.List;

@Data
public class CategoryDTO {
    private Long id;
    @NotBlank(message = "Le nom de la catégorie ne doit pas être vide")
    private String name;
    private List<Article> articles;
}
