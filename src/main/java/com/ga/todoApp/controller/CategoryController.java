package com.ga.todoApp.controller;


import com.ga.todoApp.model.Category;
import com.ga.todoApp.service.CategoryService;
import com.sun.jdi.VoidType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {


    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping("/categories/")
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }


    @GetMapping("/categories/")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }


    @GetMapping("/categories/{categoryId}")
    public Category getCategoryById(
            @PathVariable Long categoryId
    ) {
        return categoryService.getCategoryById(categoryId);
    }


    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<Void> deleteCategoryById(
            @PathVariable Long categoryId
    ) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/categories/{categoryId}")
    public Category updateCategory(
            @PathVariable Long categoryId,
            @RequestBody Category category
    ) {
        return categoryService.updateCategory(categoryId, category);
    }

}
