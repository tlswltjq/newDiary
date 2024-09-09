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
    void todoCreateTest(){
        //given 새로운 TodoTitle이 주어지면
        String title = "todo1";

        //when Todo를 생성하고 저장할 수 있다.
        Todo savedTodo = todoService.createTodo(title);


        log.info("title : {}, savedTodo.title : {}",title, savedTodo.getTodoTitle());
        //then 저장된 Todo의 todoTitle은 title과 같아야한다.
        Assertions.assertThat(savedTodo.getTodoTitle()).isEqualTo(title);
    }

}
