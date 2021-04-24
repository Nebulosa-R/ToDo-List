package com.todolist.controllers;

import java.util.List;
import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.todolist.entities.Item;
import com.todolist.services.ItemService;

@Controller 
@RequestMapping(path="/api/items") 
public class ItemController {

	@Autowired
    ItemService itemService;

    @GetMapping("/list")
    public ResponseEntity<List<Item>> getList(){
        List<Item> lista = itemService.getList();
        return new ResponseEntity<List<Item>>(lista, HttpStatus.OK);
    }
    
    @SuppressWarnings("rawtypes")
	@PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody Item item){
    	if(StringUtils.isBlank(item.getItemName()))
    		return new ResponseEntity(HttpStatus.BAD_REQUEST);
    	item.setCompleted(false);  //value for default
    	itemService.saveItem(item);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Item> get(@PathVariable Long id){
        if(!itemService.existsItemById(id))
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Item>(itemService.getItemById(id).get(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Item> get(@RequestBody Item item, @PathVariable("id") Long id){
        if(!itemService.existsItemById(id))
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(item.getItemName()))
    		return new ResponseEntity(HttpStatus.BAD_REQUEST);
        
        Item newItem = itemService.getItemById(id).get();
        newItem.setItemName(item.getItemName());
        newItem.setCompleted(item.isCompleted());
        
        itemService.saveItem(newItem);
        return new ResponseEntity<Item>(itemService.getItemById(id).get(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(!itemService.existsItemById(id))
        	return new ResponseEntity(HttpStatus.NOT_FOUND);
        itemService.deleteItem(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
