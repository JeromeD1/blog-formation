package org.wildcodeschool.blog.model.DTO;

import lombok.Data;
import org.wildcodeschool.blog.model.entity.Tag;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ArticleDTO {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long categoryId;
    private List<Long> tagIds;
}
