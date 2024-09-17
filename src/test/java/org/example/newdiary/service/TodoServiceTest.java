package org.example.newdiary.service;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.example.newdiary.entity.Todo;
import org.example.newdiary.entity.TodoList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class TodoServiceTest {
    @Autowired
    TodoService todoService;
    @Autowired
    TodoListService todoListService;

    @Test
    @DisplayName("Todo를 생성할 수 있다.")
    void createTodo() {
        //given Description과 빈 리스트가 주어지고
        String description = "goToSleep";
        TodoList todoList = todoListService.createTodoList("testList");
        //when Todo를 생성하면
        Todo todo = todoService.createTodo(description, todoList.getId());
        log.info(todo.getDescription());
        //than Description을 가진 Todo가 생성된다.
        Assertions.assertThat(todo).isNotNull();
        Assertions.assertThat(todo.getDescription()).isEqualTo(description);
    }

    @Test
    @DisplayName("Todo를 검색할 수 있다.")
    void retrieveTodo() {
        //given Todo의 Id가 주어지고
        TodoList todoList = todoListService.createTodoList("testList");
        Long listId = todoList.getId();
        Todo todo = todoService.createTodo("testTodo", listId);
        //when Todo를 검색하면
        Todo retrieved = todoService.getTodo(todo.getId());
        //than Todo가 반환된다.
        Assertions.assertThat(retrieved).isNotNull();
        Assertions.assertThat(retrieved.getDescription()).isEqualTo("testTodo");

    }

    @Test
    @DisplayName("Todo를 완료할 수 있다.")
    void doneTodo() {
        //given Todo가 주어지고
        TodoList todoList = todoListService.createTodoList("testList");
        Long listId = todoList.getId();
        Todo todo = todoService.createTodo("testTodo", listId);
        //when Todo를 완료하면
        Todo donedTodo = todoService.doneTodo(todo.getId());
        //than Todo의 isDone이 True가 된다.
        Assertions.assertThat(donedTodo.getIsDone()).isTrue();
    }

    @Test
    @DisplayName("Todo완료를 취소할 수 있다.")
    void unDoneTodo() {
        //given 이미 완료된 Todo가 주어지고
        TodoList todoList = todoListService.createTodoList("testList");
        Long listId = todoList.getId();
        Todo todo = todoService.createTodo("testTodo", listId);
        //when Todo의 완료를 취소하면
        Todo donedTodo = todoService.unDoneTodo(todo.getId());
        //than Todo의 isDone이 False가 된다.
        Assertions.assertThat(donedTodo.getIsDone()).isFalse();
    }

    @Test
    @DisplayName("Todo의 설명을 수정할 수 있다.")
    void updateTodo() {
        //given Todo와 수정할 Description이 주어지고
        TodoList todoList = todoListService.createTodoList("testList");
        Long listId = todoList.getId();
        Todo todo = todoService.createTodo("testTodo", listId);
        String newDescript = "updatedTodo";
        //when Todo의 Description를 새 Description로 수정하면
        Todo updatedTodo = todoService.updateTodo(todo.getId(), newDescript);
        //than 변경된 Description를 확인할 수 있다.
        Assertions.assertThat(updatedTodo.getDescription()).isEqualTo(newDescript);
    }

    @Test
    @DisplayName("Todo를 삭제할 수 있다.")
    void deleteTodo() {
        // TODO: 17/9/2024 지연로딩과 트랜잭션 세션을 키워드로 조사 필요 
        //given 삭제할 Todo의 Id가 주어지고
        //when Id를 이용해 삭제하면
        //than 에러가 반환된다.
        Assertions.assertThat(true).isFalse();
    }
}