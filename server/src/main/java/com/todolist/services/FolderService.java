package com.todolist.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todolist.entities.Folder;
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
		return folderRepository.findById(id);
	}

}
