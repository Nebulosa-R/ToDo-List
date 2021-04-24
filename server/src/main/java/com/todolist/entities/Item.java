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

	public Item() {
		// TODO Auto-generated constructor stub
	}
	
	public Item(String itemName, boolean completed) {
		this.setItemName(itemName);
		this.setCompleted(completed);
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

	

}
