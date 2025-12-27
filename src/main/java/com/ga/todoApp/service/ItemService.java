package com.ga.todoApp.service;


import com.ga.todoApp.exception.InformationExistException;
import com.ga.todoApp.exception.InformationNotFoundException;
import com.ga.todoApp.model.Category;
import com.ga.todoApp.model.Item;
import com.ga.todoApp.repository.CategoryRepository;
import com.ga.todoApp.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private ItemRepository itemRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public void setItemRepository(ItemRepository itemRepository, CategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
    }

    public Item createItem(Long categoryId, Item itemObject) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new InformationNotFoundException("Category Not Found")
        );

        itemObject.setCategory(category);
        return itemRepository.save(itemObject);

    }


    public List<Item> getAllItem(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new InformationNotFoundException("Category Not Found")
        );

        return itemRepository.findByCategoryId(categoryId);
    }

    public void deleteItem(Long id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
        } else {
            throw new InformationNotFoundException("item does not exist");
        }
    }


    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElseThrow(
                () -> new InformationNotFoundException("item not found")
        );
    }

    public Item getItemByCategory(Long categoryId, Long itemId) {
        return itemRepository.findByIdAndCategoryId(itemId, categoryId).orElseThrow(
                () -> new InformationNotFoundException("item does not exist")
        );
    }


    public Item updateItem(Long itemId, Item itemObject) {
        Item existingItem = itemRepository.findById(itemId).orElseThrow(
                () -> new InformationNotFoundException("item not found")
        );

        if (itemObject.getName() != null) {
            existingItem.setName(itemObject.getName());
        }
        if (itemObject.getDescription() != null) {
            existingItem.setDescription(itemObject.getDescription());
        }

        if (itemObject.getCategory() != null) {
            existingItem.setCategory(itemObject.getCategory());
        }

        return itemRepository.save(existingItem);
    }


}
