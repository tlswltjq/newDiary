package org.example.newdiary.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TodoServiceTest {
    @Autowired
    TodoListService todoService;

    @Test
    @DisplayName("Todo를 생성할 수 있다.")
    void createTodo() {
        //given Description이 주어지고
        //when Todo를 생성하면
        //than Description을 가진 Todo가 생성된다.
        Assertions.assertThat(true).isFalse();
    }

    @Test
    @DisplayName("Todo를 검색할 수 있다.")
    void retrieveTodo() {
        //given Todo의 Id가 주어지고
        //when Todo를 검색하면
        //than Todo가 반환된다.
        Assertions.assertThat(true).isFalse();
    }

    @Test
    @DisplayName("Todo를 완료할 수 있다.")
    void doneTodo() {
        //given Todo가 주어지고
        //when Todo를 완료하면
        //than Todo의 isDone이 True가 된다.
        Assertions.assertThat(true).isFalse();
    }

    @Test
    @DisplayName("Todo완료를 취소할 수 있다.")
    void unDoneTodo() {
        //given 이미 완료된 Todo가 주어지고
        //when Todo의 완료를 취소하면
        //than Todo의 isDone이 False가 된다.
        Assertions.assertThat(true).isFalse();
    }

    @Test
    @DisplayName("Todo의 설명을 수정할 수 있다.")
    void updateTodo() {
        //given Todo와 수정할 Description이 주어지고
        //when Todo의 Description를 새 Description로 수정하면
        //than 변경된 Description를 확인할 수 있다.
        Assertions.assertThat(true).isFalse();
    }

    @Test
    @DisplayName("Todo를 삭제할 수 있다.")
    void deleteTodo() {
        //given 삭제할 Todo의 Id가 주어지고
        //when Id를 이용해 삭제하면
        //than 에러가 반환된다.
        Assertions.assertThat(true).isFalse();
    }
}