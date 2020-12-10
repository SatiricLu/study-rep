package com.satiric.doit.Tables;


import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface categoriesRepository extends CrudRepository<Category, String> {
    List<Category> findAllByName(String name);
    Category findByName(String name);
}
