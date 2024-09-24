package org.example.newdiary.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.newdiary.entity.Todo;
import org.example.newdiary.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    @PostMapping
    ResponseEntity<?> addTodo(@RequestParam(name = "desc") String description, @RequestParam(name = "list") Long listId) {
        Todo todo = todoService.createTodo(description, listId);
        return ResponseEntity.ok().body(todo.getDescription() + "생성 완료");
    }

    @PutMapping("/done/{todo}")
    ResponseEntity<?> doneTodo(@PathVariable(name = "todo") Long todo) {
        Todo donedTodo = todoService.doneTodo(todo);
        log.info("{}완료", donedTodo.getId());
        return ResponseEntity.ok().body(donedTodo.getDescription() + "완료");
    }

    @PutMapping("/undone/{todo}")
    ResponseEntity<?> unDoneTodo(@PathVariable(name = "todo") Long todo) {
        Todo unDoneTodo = todoService.unDoneTodo(todo);
        log.info("{}완료취소", unDoneTodo.getId());
        return ResponseEntity.ok().body(unDoneTodo.getDescription() + "완료 취소");
    }

    @DeleteMapping("/{todo}")
    ResponseEntity<?> deleteTodo(@PathVariable(name = "todo") Long todo){
        todoService.deleteTodo(todo);
        return ResponseEntity.ok().body(todo+ "삭제 완료");
    }
}
