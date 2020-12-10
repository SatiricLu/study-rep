package com.satiric.doit.Tables;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface plannersRepository extends CrudRepository<Planner, String> {
    List<Planner> findAllByUser(String user);
    Planner findById(Integer id);
}
