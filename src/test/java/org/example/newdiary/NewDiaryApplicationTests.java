package org.example.newdiary;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.example.newdiary.entity.Todo;
import org.example.newdiary.service.TodoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class NewDiaryApplicationTests {
    @Autowired
    TodoService todoService;

//    @BeforeEach
//    void before() {
//        log.info("모든 테스트 전 실행");
//    }
//
//    @AfterEach
//    void after() {
//        log.info("모든 테스트 후 실행");
//    }

    @Test
    @DisplayName("Todo엔티티 생성 테스트")
    void todoCreateTest() {
        //given 새로운 TodoTitle이 주어지면
        String title = "todo1";

        //when Todo를 생성하고 저장할 수 있다.
        Todo savedTodo = todoService.createTodo(title, 1);


        log.info("title : {}, savedTodo.title : {}", title, savedTodo.getTodoTitle());
        //then 저장된 Todo의 todoTitle은 title과 같아야한다.
        Assertions.assertThat(savedTodo.getTodoTitle()).isEqualTo(title);
    }

    @Test
    @DisplayName("단일 Todo 조회하기")
    void todoGetTest() {
        //given 엔티티가 저장되고 엔티티의 아이디가 주어지면
        String title = "newTodo";
        for (int i = 0; i < 3; i++) {
            // newTodo1~3 생성 및 저장
            todoService.createTodo(title + (i + 1), 1);
        }
        //when 해당 아이디를 이용해 엔티티를 가져올 수 있다.
        for (int i = 0; i < 3; i++) {
            Todo todo = todoService.getTodo(i + 1);
            //then 검색된 엔티티의 타이틀과 생성한 엔티티의 타이틀은 같아야한다.
            Assertions.assertThat(todo.getTodoTitle()).isEqualTo(title + (i + 1));
        }
    }

    @Test
    @DisplayName("다건 Todo 조회하기")
    void todoListGetTest() {
        //given Todo가 저장되고 엔티티가 저장될 todoList의 아이디가 제공되면
        Integer listId = 100;
        int number = 3;
        createTestTodoListDate(number, listId);
        //when TodoList의 아이디를 이용해 해당 리스트에 포함된 모든 todo가 리스트로 반환하면.
        List<Todo> todoList = todoService.getTodoList(listId);
        //then 생성한 todo의 개수와 리스트의 크기는 같아야한다.
        Assertions.assertThat(todoList.size()).isEqualTo(number);
    }

    /**
    리스트 아이디 listId에 n개 Todo를 생성해주는 메서드
     */
    private void createTestTodoListDate(int number, Integer listId) {
        for (int i = 0; i < number; i++) {
            String title = "newTodo" + (i + 1);
            todoService.createTodo(title, listId);
            log.info("todo : {}생성 완료", title);
        }
    }
}
