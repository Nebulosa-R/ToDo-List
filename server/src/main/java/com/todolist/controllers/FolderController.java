package com.todolist.controllers;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.todolist.dtos.FolderDto;
import com.todolist.entities.Folder;
import com.todolist.services.FolderService;

@Controller 
@RequestMapping(path="/api/folders")
public class FolderController {

	@Autowired
    FolderService folderService;

    @GetMapping("/list")
    public ResponseEntity<List<Folder>> getList(){
        List<Folder> lista = folderService.getList();
        for(Folder folder: lista)
        	folder.setItems(null);
        return new ResponseEntity<List<Folder>>(lista, HttpStatus.OK);
    }
    
	@PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody Folder folder){
    	if(StringUtils.isBlank(folder.getFolderName()))
    		return new ResponseEntity(HttpStatus.BAD_REQUEST);
    	//folder.setItems(null);  //item list is null for default
    	folderService.saveFolder(folder);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<FolderDto> get(@PathVariable Long id){
        if(!folderService.existsFolderById(id))
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        //folderService.getFolderById(id).get(), 
        FolderDto folder = folderService.getFolderDtoById(id); 
        		
        return new ResponseEntity<FolderDto>(folder, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Folder> get(@RequestBody Folder folder, @PathVariable("id") Long id){
        if(!folderService.existsFolderById(id))
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(folder.getFolderName()))
    		return new ResponseEntity(HttpStatus.BAD_REQUEST);
        
        Folder newFolder = folderService.getFolderById(id).get();
        newFolder.setFolderName(folder.getFolderName());
        
        folderService.saveFolder(newFolder);
        return new ResponseEntity<Folder>(folderService.getFolderById(id).get(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(!folderService.existsFolderById(id))
        	return new ResponseEntity(HttpStatus.NOT_FOUND);
        //folderService.deleteFolderItems(id);
        folderService.deleteFolder(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    
}
