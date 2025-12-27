package com.ga.todoApp.repository;


import com.ga.todoApp.model.Category;
import com.ga.todoApp.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  ItemRepository extends JpaRepository<Item,Long> {
    Item findByName(String name);
    List<Item> findByCategory(Category category);
}
