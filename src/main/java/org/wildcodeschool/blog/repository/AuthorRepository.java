package org.wildcodeschool.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wildcodeschool.blog.model.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
