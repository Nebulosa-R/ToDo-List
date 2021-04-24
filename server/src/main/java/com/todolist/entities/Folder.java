package com.todolist.entities;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "Folders")
public class Folder {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String folderName;
    @OneToMany(mappedBy="folder",orphanRemoval=true)
    private List<Item> items;

	public Folder() {
		// TODO Auto-generated constructor stub
	}
	
	public Folder(String folderName, List<Item> items) {
		this.folderName = folderName;
		this.items = items;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}
