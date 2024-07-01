package org.wildcodeschool.blog.model.DTO;

import lombok.Data;

import java.util.List;

@Data
public class TagDTO {
    private Long id;
    private String name;
    private List<Long> articleIds;
}
