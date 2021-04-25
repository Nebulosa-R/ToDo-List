package com.todolist.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todolist.dtos.FolderDto;
import com.todolist.dtos.ItemDto;
import com.todolist.entities.Folder;
import com.todolist.entities.Item;
import com.todolist.repositories.FolderRepository;

@Service
@Transactional
public class FolderService {
	@Autowired
	private FolderRepository folderRepository;

	public void saveFolder(Folder folder) {
		folderRepository.save(folder);
	}
	
	public void deleteFolder(Long id) {
		folderRepository.deleteById(id);
	}
	
	public List<Folder> getList(){
		return (List<Folder>) folderRepository.findAll();
	}
	
	public boolean existsFolderById(Long id) {
		return folderRepository.existsById(id);
	}
	
	public Optional<Folder> getFolderById(Long id) {
		Optional<Folder> folder = folderRepository.findById(id);
		for(Item item: folder.get().getItems()) 
			item.setFolder(null);
		return folder;
	}

	/*public FolderDto getFolderDtoById(Long id) {
		Folder folder = folderRepository.findById(id).get();
		//List<ItemDto> items = new ArrayList<ItemDto>();
		for(Item item: folder.getItems()) {
			item.setFolder(null);
			//items.add(new ItemDto(item.getItemName(), item.isCompleted()));
		}
		//FolderDto folderDto = new FolderDto(folder.getFolderName(), items);
		return folderDto;
	}*/

}
