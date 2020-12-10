package com.satiric.doit.Tables;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface userRepository extends CrudRepository<User, String> {
    User findByLogin(String login);
    User getByLogin(String login);
    Boolean existsUserByLogin(String login);
}
