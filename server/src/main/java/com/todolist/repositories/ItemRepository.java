package com.todolist.repositories;

import org.springframework.data.repository.CrudRepository;
import com.todolist.entities.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {

}
