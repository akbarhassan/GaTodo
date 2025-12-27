package com.ga.todoApp.service;


import com.ga.todoApp.exception.InformationExistException;
import com.ga.todoApp.exception.InformationNotFoundException;
import com.ga.todoApp.model.Item;
import com.ga.todoApp.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private ItemRepository itemRepository;

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item createItem(Item itemObject) {
        Item item = itemRepository.findByName(itemObject.getName());

        if (item == null) {
            return itemRepository.save(itemObject);
        } else {
            throw new InformationExistException("item already exists");
        }
    }


    public List<Item> getAllItem() {
        return itemRepository.findAll();
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
