package org.example.newdiary.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.newdiary.entity.Todo;
import org.example.newdiary.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public Todo createTodo(String todoTitle, Integer listId) {
        Todo newTodo = new Todo().builder()
                .todoTitle(todoTitle)
                .done(false)
                .listId(listId)
                .build();
        return todoRepository.save(newTodo);
    }

    public Todo getTodo(Integer todoId) {
        return todoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException("Wrong Id"));
    }

    public List<Todo> getTodoList(Integer listId) {
        return todoRepository.findByListId(listId);
    }

    public Todo updateTodoTitle(Integer todoId, String newTitle) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException("Wrong Id"));
        Todo updated = todo.updateTitle(newTitle);
        return todoRepository.save(updated);
    }
    public Todo todoDoneToggle(Integer todoId){
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException("Wrong Id"));
        todo.toggle();
        return todoRepository.save(todo);
    }
    public void deleteTodo(Integer todoId){
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException("Wrong Id"));
        todoRepository.delete(todo);
    }

    public void deleteAll(){
        todoRepository.deleteAll();
    }
}
