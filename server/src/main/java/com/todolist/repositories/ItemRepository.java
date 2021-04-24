package com.todolist.repositories;

import org.springframework.data.repository.CrudRepository;
import com.todolist.entities.Item;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {

}
