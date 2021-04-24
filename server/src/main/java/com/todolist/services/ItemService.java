package com.todolist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todolist.entities.Item;
import com.todolist.repositories.ItemRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;

	public void saveItem(Item item) {
		itemRepository.save(item);
	}
	
	public void deleteItem(Long id) {
		itemRepository.deleteById(id);
	}
	
	public List<Item> getList(){
		return (List<Item>) itemRepository.findAll();
	}
	
	public boolean existsItemById(Long id) {
		return itemRepository.existsById(id);
	}
	
	public Optional<Item> getItemById(Long id) {
		return itemRepository.findById(id);
	}
}
