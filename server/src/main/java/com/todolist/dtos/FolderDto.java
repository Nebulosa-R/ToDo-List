package com.todolist.dtos;

import java.util.List;

public class FolderDto {
	
	private String folderName;
    private List<ItemDto> items;


	public FolderDto() {
		// TODO Auto-generated constructor stub
	}

	public FolderDto(String folderName, List<ItemDto> items) {
		this.setFolderName(folderName);
		this.setItems(items);
	}

	
	public String getFolderName() {
		return folderName;
	}


	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}


	public List<ItemDto> getItems() {
		return items;
	}


	public void setItems(List<ItemDto> items) {
		this.items = items;
	}

}
