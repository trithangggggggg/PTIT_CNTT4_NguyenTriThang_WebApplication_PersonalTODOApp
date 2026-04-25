package org.example.personaltodoapp.repository;

import org.example.personaltodoapp.Entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITodoRepository extends JpaRepository<Todo, Long> {
}
