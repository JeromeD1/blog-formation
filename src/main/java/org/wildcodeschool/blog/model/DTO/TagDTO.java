package org.wildcodeschool.blog.model.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class TagDTO {
    private Long id;
    @NotBlank(message = "Le nom du tag ne doit pas être vide")
    private String name;
    private List<Long> articleIds;
}
