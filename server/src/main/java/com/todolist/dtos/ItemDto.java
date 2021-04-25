package com.todolist.dtos;

public class ItemDto {
	
	private Long id;
	private String itemName;  
    private boolean completed;
    private Long folder_id;

	public ItemDto() {
		// TODO Auto-generated constructor stub
	}

	public ItemDto(String itemName, boolean completed) {
		this.setItemName(itemName);
		this.setCompleted(completed);
	}
	
	public ItemDto(String itemName, boolean completed, Long folder_id, Long id) {
		this.setItemName(itemName);
		this.setCompleted(completed);
		this.folder_id = folder_id;
		this.id = id;
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

	public Long getFolder_id() {
		return folder_id;
	}

	public Long getId() {
		return id;
	}

}
