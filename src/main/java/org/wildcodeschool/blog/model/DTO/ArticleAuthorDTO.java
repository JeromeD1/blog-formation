package org.wildcodeschool.blog.model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class ArticleAuthorDTO {

    @NotNull(message = "articleId ne doit pas être null")
    @Positive(message = "articleId doit être positif")
    private Long articleId;

    @NotNull(message = "authorId ne doit pas être null")
    @Positive(message = "authorId doit être positif")
    private Long authorId;

    @NotBlank(message = "La contribution ne doit pas être vide")
    @Size(min = 10, message = "Le contenu doit contenir au moins 10 caractères")
    private String contribution;
}
