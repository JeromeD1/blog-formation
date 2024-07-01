package org.wildcodeschool.blog.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wildcodeschool.blog.mapper.TagMapper;
import org.wildcodeschool.blog.model.DTO.TagDTO;
import org.wildcodeschool.blog.model.entity.Tag;
import org.wildcodeschool.blog.repository.ArticleRepository;
import org.wildcodeschool.blog.repository.TagRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/tag")
public class TagController {
    private TagRepository tagRepository;
    private TagMapper tagMapper;
    private ArticleRepository articleRepository;

    @GetMapping
    public ResponseEntity<List<TagDTO>> getAllTags() {
        List<Tag> tags = tagRepository.findAll();
        if (tags.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<TagDTO> tagDTOs = tags.stream()
                .map(tagMapper::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tagDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDTO> getTagById(@PathVariable Long id) {
        Tag tag = tagRepository.findById(id).orElse(null);
        if (tag == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tagMapper.convertToDTO(tag));
    }

    @PostMapping
    public ResponseEntity<TagDTO> createTag(@RequestBody TagDTO tagDTO) {
        Tag tag = tagMapper.convertToEntity(tagDTO);
        Tag savedTag = tagRepository.save(tag);
        return ResponseEntity.status(201).body(tagMapper.convertToDTO(savedTag));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagDTO> updateTag(@PathVariable Long id, @RequestBody TagDTO tagDTO) {
        Tag tag = tagRepository.findById(id).orElse(null);
        if (tag == null) {
            return ResponseEntity.notFound().build();
        }
        tag.setName(tagDTO.getName());
        Tag updatedTag = tagRepository.save(tag);
        return ResponseEntity.ok(tagMapper.convertToDTO(updatedTag));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
        Tag tag = tagRepository.findById(id).orElse(null);
        if (tag == null) {
            return ResponseEntity.notFound().build();
        }
        tagRepository.delete(tag);
        return ResponseEntity.noContent().build();
    }


}
