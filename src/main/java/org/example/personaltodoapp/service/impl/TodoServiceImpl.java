package org.example.personaltodoapp.service.impl;


import lombok.RequiredArgsConstructor;
import org.example.personaltodoapp.Entity.Todo;
import org.example.personaltodoapp.repository.ITodoRepository;
import org.example.personaltodoapp.service.ITodoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements ITodoService {
    private final ITodoRepository todoRepository;


    @Override
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public void save(Todo todo) {
        todoRepository.save(todo);
    }

    @Override
    public Todo findById(Long id) {
        return todoRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }

}
