package org.example.personaltodoapp.service;

import org.example.personaltodoapp.Entity.Todo;

import java.util.List;

public interface ITodoService {
    List<Todo> findAll();

    void save(Todo todo);

    Todo findById(Long id);

    void deleteById(Long id);
}
