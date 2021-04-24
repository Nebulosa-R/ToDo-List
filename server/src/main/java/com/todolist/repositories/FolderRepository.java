package com.todolist.repositories;

import org.springframework.data.repository.CrudRepository;

import com.todolist.entities.Folder;

public interface FolderRepository extends CrudRepository<Folder, Long> {

}
