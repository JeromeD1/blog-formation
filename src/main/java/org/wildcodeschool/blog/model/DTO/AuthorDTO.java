package org.wildcodeschool.blog.model.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class AuthorDTO {
    private Long id;
    @NotBlank(message = "Le prénom ne doit pas être vide")
    private String firstname;
    @NotBlank(message = "Le nom ne doit pas être vide")
    private String lastname;
    private List<ArticleAuthorDTO> articleAuthors;
}
