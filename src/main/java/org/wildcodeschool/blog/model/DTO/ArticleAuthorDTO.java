package org.wildcodeschool.blog.model.DTO;

import lombok.Data;

@Data
public class ArticleAuthorDTO {
    private Long articleId;
    private Long authorId;
    private String contribution;
}
