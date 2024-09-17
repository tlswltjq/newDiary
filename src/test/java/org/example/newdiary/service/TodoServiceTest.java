package org.example.newdiary.service;

import org.assertj.core.api.Assertions;
import org.example.newdiary.entity.Todo;
import org.example.newdiary.entity.TodoList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TodoServiceTest {
    @Autowired
    TodoService todoService;
    @Test
    @DisplayName("List없이 Todo 바로 생성하기")
    void createTodoWithoutList() {
        //given 생성할 Todo명이 주어지고
        String todoTitle = "newTitle";

        //when Todo를 생성하면
        Todo todo = todoService.createTodo(todoTitle);

        //then NewTodo!리스트에 속한 Todo가 생성된다.
        Todo retrived = todoService.getTodo(todo.getTodoId());

        Assertions.assertThat(retrived).isNotNull();
        Assertions.assertThat(retrived.getTodoTitle()).isEqualTo(todoTitle);
        Assertions.assertThat(retrived.getListId()).isNotNull();
        Assertions.assertThat(retrived.getListId().getListTitle()).isEqualTo("NewTodoList!");

    }
    @Test
    @DisplayName("List에 Todo추가하기")
    void addTodoToList(){
        assertThat(true).isFalse();
    }
    @Test
    @DisplayName("빈 List 생성하기")
    void createNewTodoList(){
        //given
        //when 빈 List를 생성하면
        TodoList todoList = todoService.createTodoList();
        //than Title이 NewTodoList!인 새 List가 생성된다.
        Assertions.assertThat(todoList).isNotNull();
        Assertions.assertThat(todoList.getListTitle()).isEqualTo("NewTodoList!");
    }
    @Test
    @DisplayName("Title이 주어진 빈 List 생성하기")
    void createNewTodoListWithTitle(){
        //given 생성할 TodoList의 Title이 주어지고
        String title = "todotitle";
        //when 빈 List를 생성하면
        TodoList todoList = todoService.createTodoList(title);
        //than 주어진 Title이 Title인 새 List가 생성된다.
        Assertions.assertThat(todoList).isNotNull();
        Assertions.assertThat(todoList.getListTitle()).isEqualTo(title);
    }
    @Test
    @DisplayName("Todo완료하기")
    void TodoDone(){
        assertThat(true).isFalse();
    }

    @Test
    @DisplayName("Todo완료 취소하기")
    void TodoDoneRevoke() {
        assertThat(true).isFalse();
    }
    @Test
    @DisplayName("Todo 타이틀 수정하기")
    void updateTodoTitle(){
        assertThat(true).isFalse();
    }
    @Test
    @DisplayName("TodoList 타이틀 수정하기")
    void updateTodoListTitle(){
        assertThat(true).isFalse();
    }
    @Test
    @DisplayName("TodoList 삭제하기")
    void removeTodoList(){
        assertThat(true).isFalse();
    }
//    @Test
//    @DisplayName("")
//    void (){
//    //given
//    //when
//    //than
//        assertThat(true).isFalse();
//    }
}