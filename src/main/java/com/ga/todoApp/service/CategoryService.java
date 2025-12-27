package com.ga.todoApp.service;

import com.ga.todoApp.exception.InformationExistException;
import com.ga.todoApp.exception.InformationNotFoundException;
import com.ga.todoApp.model.Category;
import com.ga.todoApp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public Category createCategory(Category categoryObject) {
        Category category = categoryRepository.findByName(categoryObject.getName());
        if (category == null) {
            return categoryRepository.save(categoryObject);
        } else {
            throw new InformationExistException("Category already exists");
        }
    }

    public void deleteCategory(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new InformationNotFoundException("category does not exist");
        }
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(
                () -> new InformationNotFoundException("Category does not exist")
        );
    }


    public Category updateCategory(Long categoryId, Category categoryObject) {
        Category existingCategory = categoryRepository.findById(categoryId).orElseThrow(
                () -> new InformationNotFoundException("Category does not exist")
        );

        if (categoryObject.getName() != null) {
            existingCategory.setName(categoryObject.getName());
        }

        if (categoryObject.getDescription() != null) {
            existingCategory.setDescription(categoryObject.getDescription());
        }

        return categoryRepository.save(existingCategory);
    }

}
