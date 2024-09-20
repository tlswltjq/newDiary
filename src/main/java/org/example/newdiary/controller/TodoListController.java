package org.example.newdiary.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.newdiary.dto.GetTodoListResponse;
import org.example.newdiary.dto.GetTodoResponse;
import org.example.newdiary.entity.TodoList;
import org.example.newdiary.service.TodoListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/list")
public class TodoListController {
    private final TodoListService todoListService;
    @GetMapping()
    ResponseEntity<?> getTodoList(@RequestParam(name = "listId") Long listId){
        TodoList todoList = todoListService.getTodoList(listId);
        return ResponseEntity.ok().body(new GetTodoListResponse(todoList));
    }
}
