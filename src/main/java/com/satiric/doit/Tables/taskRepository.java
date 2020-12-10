package com.satiric.doit.Tables;

import org.springframework.data.repository.CrudRepository;

public interface taskRepository extends CrudRepository<Task, String> {
    Task findByName(String name);
}

