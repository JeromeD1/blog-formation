package org.wildcodeschool.blog.model.DTO;

import lombok.Data;

import java.util.List;

@Data
public class AuthorDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private List<ArticleAuthorDTO> articleAuthors;
}
