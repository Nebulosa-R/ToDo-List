package com.todolist.controllers;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.todolist.dtos.ItemDto;
import com.todolist.entities.Folder;
import com.todolist.entities.Item;
import com.todolist.services.FolderService;
import com.todolist.services.ItemService;

@Controller 
@RequestMapping(path="/api/items") 
public class ItemController {

	@Autowired
    ItemService itemService;
	@Autowired
    FolderService folderService;

    @GetMapping("/list")
    private ResponseEntity<List<Item>> getList(){
        List<Item> lista = itemService.getList();
        return new ResponseEntity<List<Item>>(lista, HttpStatus.OK);
    }
    
    @SuppressWarnings("rawtypes")
	@PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody ItemDto item){
    	if(StringUtils.isBlank(item.getItemName()))
    		return new ResponseEntity(HttpStatus.BAD_REQUEST);
    	if((item.getFolder_id() == null))
    		return new ResponseEntity(HttpStatus.BAD_REQUEST);
    	if (!folderService.existsFolderById(item.getFolder_id()))
    		return new ResponseEntity(HttpStatus.NOT_FOUND);
    	Optional<Folder> folder = folderService.getFolderById(item.getFolder_id());
    	item.setCompleted(false);  //value for default
    	Item newItem = new Item(item.getItemName(),item.isCompleted(), folder.get());
    	itemService.saveItem(newItem);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<ItemDto> get(@PathVariable Long id){
        if(!itemService.existsItemById(id))
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        //Item item = itemService.getItemById(id).get();
        ItemDto item = itemService.getItemDtoById(id);
        return new ResponseEntity<ItemDto>(item, HttpStatus.OK);
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
