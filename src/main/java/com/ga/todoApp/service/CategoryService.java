package com.ga.todoApp.service;

import com.ga.todoApp.exception.InformationExistException;
import com.ga.todoApp.exception.InformationNotFoundException;
import com.ga.todoApp.model.Category;
import com.ga.todoApp.model.User;
import com.ga.todoApp.repository.CategoryRepository;
import com.ga.todoApp.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public static User getCurrentLoggedInUser() {
        MyUserDetails userDetails =
                (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUser();
    }

    public Category createCategory(Category categoryObject) {
        System.out.println("Service Calling createCategory ==>");

        Category category = categoryRepository.findByUserIdAndName(
                CategoryService.getCurrentLoggedInUser().getId()
                , categoryObject.getName());

        if (category != null) {
            throw new InformationExistException("category with name " + category.getName() + " already exists");
        } else {
            categoryObject.setUser(getCurrentLoggedInUser());
            return categoryRepository.save(categoryObject);
        }
    }


    public String deleteCategory(Long categoryId) {
        Category category = categoryRepository.findByIdAndUserId(categoryId, CategoryService.getCurrentLoggedInUser().getId());
        if (category == null) {
            throw new InformationNotFoundException("category with id " + categoryId + " not found");
        } else {
            categoryRepository.deleteById(categoryId);
            return "category with id " + categoryId + " has been successfully deleted";
        }
    }

    public List<Category> getAllCategories() {
        List<Category> category = categoryRepository.findByUserId(CategoryService.getCurrentLoggedInUser().getId());
        if (category.isEmpty()) {
            throw new InformationNotFoundException("no categories found for user id " + CategoryService.getCurrentLoggedInUser().getId());
        } else {
            return category;
        }
    }


//    public Category getCategoryById(Long categoryId) {
//        return categoryRepository.findById(categoryId).orElseThrow(
//                () -> new InformationNotFoundException("Category does not exist")
//        );
//    }

    public Category getCategoryById(Long categoryId) {
        Category category = categoryRepository.findByIdAndUserId(categoryId, CategoryService.getCurrentLoggedInUser().getId());
        if (category == null) {
            throw new InformationNotFoundException("category with id " + categoryId + " not found");
        } else {
            return category;
        }
    }


    public Category updateCategory(Long categoryId, Category categoryObject) {
        Category category = categoryRepository.findByIdAndUserId(categoryId, CategoryService.getCurrentLoggedInUser().getId());
        if (category == null) {
            throw new InformationNotFoundException("category with id " + categoryId + " not found");
        } else {
            category.setDescription(categoryObject.getDescription());
            category.setName(categoryObject.getName());
            category.setUser(CategoryService.getCurrentLoggedInUser());
            return categoryRepository.save(category);
        }
    }

}
