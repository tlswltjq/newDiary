package org.example.newdiary.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.newdiary.dto.GetTodoListResponse;
import org.example.newdiary.entity.TodoList;
import org.example.newdiary.service.TodoListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/list")
public class TodoListController {
    private final TodoListService todoListService;
    @GetMapping
    ResponseEntity<?> getTodoList(@RequestParam(name = "listId") Long listId){
        TodoList todoList = todoListService.getTodoList(listId);
        return ResponseEntity.ok().body(new GetTodoListResponse(todoList));
    }
    @PostMapping
    ResponseEntity<?> createTodoList(@RequestParam(name = "name") String name){
        TodoList todoList = todoListService.createTodoList(name);
        return ResponseEntity.ok().body(todoList.getName() + " 생성완료");
    }
    @PutMapping("/{listId}")
    ResponseEntity<?> updateTodoListName(@RequestParam(name = "name") String name, @PathVariable("listId") Long listId){
        TodoList todoList = todoListService.updateTodoListName(name, listId);
        return ResponseEntity.ok().body(new GetTodoListResponse(todoList));
    }
    @DeleteMapping("/{listId}")
    ResponseEntity<?> deleteTodoList(@PathVariable("listId") Long listId){
        todoListService.deleteTodoList(listId);
        return ResponseEntity.ok().body(listId + "삭제 완료");
    }
}
