package com.ga.todoApp.controller;


import com.ga.todoApp.model.Item;
import com.ga.todoApp.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/categories/{categoryId}/items/")
    public Item createItem(
            @PathVariable Long categoryId,
            @RequestBody Item item
    ) {
        return itemService.createItem(categoryId, item);
    }


    @GetMapping("/categories/{categoryId}/items/")
    public List<Item> getItems(
            @PathVariable Long categoryId
    ) {
        return itemService.getAllItemsByCategory(categoryId);
    }

    @GetMapping("/categories/{categoryId}/items/{itemId}")
    public Item getItem(
            @PathVariable Long categoryId,
            @PathVariable Long itemId
    ) {
        return itemService.getItemByCategory(categoryId, itemId);
    }


    @DeleteMapping("/categories/{categoryId}/items/{itemId}")
    public ResponseEntity<Void> deleteItem(
            @PathVariable Long categoryId,
            @PathVariable Long itemId
    ) {
        itemService.deleteItem(categoryId, itemId);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/categories/{categoryId}/items/{itemId}")
    public Item updateItem(
            @PathVariable Long categoryId,
            @PathVariable Long itemId,
            @RequestBody Item item
    ) {
        return itemService.updateItem(categoryId, itemId, item);
    }

}
