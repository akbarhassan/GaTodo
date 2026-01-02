package com.ga.todoApp.repository;


import com.ga.todoApp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);

    Category findByIdAndUserId(Long categoryId, Long userId);

    List<Category> findByUserId(Long userId);

    Category findByUserIdAndName(Long userId, String categoryName);
}


