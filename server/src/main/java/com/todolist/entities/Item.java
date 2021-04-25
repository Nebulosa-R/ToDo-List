package com.todolist.entities;

import javax.persistence.*;

@Entity
@Table(name = "Items")
public class Item { 
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemName;  
    private boolean completed;
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="folder_id")
    private Folder folder;

	public Item() {
		// TODO Auto-generated constructor stub
	}
	
	public Item(String itemName, boolean completed) {
		this.setItemName(itemName);
		this.setCompleted(completed);
	}

	public Item(String itemName, boolean completed, Folder folder) {
		this.setItemName(itemName);
		this.setCompleted(completed);
		this.setFolder(folder);
	}
	
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public Folder getFolder() {
		return folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	public Long getId() {
		return id;
	}

}
