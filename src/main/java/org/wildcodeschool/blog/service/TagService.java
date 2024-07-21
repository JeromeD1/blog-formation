package org.wildcodeschool.blog.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.wildcodeschool.blog.mapper.TagMapper;
import org.wildcodeschool.blog.model.DTO.TagDTO;
import org.wildcodeschool.blog.model.entity.Tag;
import org.wildcodeschool.blog.repository.TagRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public List<TagDTO> findAll() {
        List<Tag> categories = tagRepository.findAll();
        return categories.stream().map(tagMapper::convertToDTO).toList();
    }

    public Optional<TagDTO> findById(Long id) {
        return tagRepository.findById(id).map(tagMapper::convertToDTO);
    }

    public TagDTO create(TagDTO tagDTO) {
        Tag tag = tagMapper.convertToEntity(tagDTO);
        Tag savedTag = tagRepository.save(tag);
        return tagMapper.convertToDTO(savedTag);
    }

    public Optional<TagDTO> update(Long id, TagDTO tagDTO) {
        Optional<Tag> tag = tagRepository.findById(id);
        if (tag.isPresent()) {
            return Optional.of(tagMapper.convertToDTO(tag.get()));
        } else {
            return Optional.empty();
        }
    }

    public boolean delete(Long id){
        Optional<Tag> tag = tagRepository.findById(id);
        if (tag.isPresent()) {
            tagRepository.delete(tag.get());
            return true;
        } else {
            return false;
        }
    }
}
