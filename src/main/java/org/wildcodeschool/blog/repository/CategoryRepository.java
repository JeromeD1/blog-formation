package org.wildcodeschool.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wildcodeschool.blog.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
