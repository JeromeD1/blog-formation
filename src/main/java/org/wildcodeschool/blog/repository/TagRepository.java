package org.wildcodeschool.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wildcodeschool.blog.model.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}
