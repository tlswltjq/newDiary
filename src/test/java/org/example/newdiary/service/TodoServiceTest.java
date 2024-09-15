package org.example.newdiary.service;

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

        assertThat(true).isFalse();
    }
    @Test
    @DisplayName("List에 Todo추가하기")
    void addTodoToList(){
        assertThat(true).isFalse();
    }
    @Test
    @DisplayName("빈 List 생성하기")
    void createNewTodoList(){
        assertThat(true).isFalse();
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
//        assertThat(true).isFalse();
//    }
}