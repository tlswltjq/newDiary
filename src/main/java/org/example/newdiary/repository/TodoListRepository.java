package org.example.newdiary.repository;

import org.example.newdiary.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoListRepository extends JpaRepository<TodoList, Integer> {
}
