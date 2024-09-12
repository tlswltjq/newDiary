package org.example.newdiary;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.example.newdiary.entity.Todo;
import org.example.newdiary.service.TodoService;
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
        // newTodo1~3 생성 및 저장
        Todo todo1 = todoService.createTodo(title + 1, 1);
        Todo todo2 = todoService.createTodo(title + 2, 1);
        Todo todo3 = todoService.createTodo(title + 3, 1);
        //when 해당 아이디를 이용해 엔티티를 가져올 수 있다.
        Todo retrived1 = todoService.getTodo(todo1.getTodoId());
        Todo retrived2 = todoService.getTodo(todo2.getTodoId());
        Todo retrived3 = todoService.getTodo(todo3.getTodoId());
        //then 검색된 엔티티의 타이틀과 생성한 엔티티의 타이틀은 같아야한다.
        Assertions.assertThat(retrived1.getTodoTitle()).isEqualTo(todo1.getTodoTitle());
        Assertions.assertThat(retrived2.getTodoTitle()).isEqualTo(todo2.getTodoTitle());
        Assertions.assertThat(retrived3.getTodoTitle()).isEqualTo(todo3.getTodoTitle());
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

    @Test
    @DisplayName("Todo 제목 수정하기")
    void todoUpdateTest() {
        //given todoId와 새 todoTitle이 주어지면
        int number = 3;
        int listId = 100;
        createTestTodoListDate(number, listId);
        String newTodoTitle = "updated!!!";

        //when todoId에 해당하는 엔티티를 찾고 todoTitle로 수정하면
        List<Todo> todoList = todoService.getTodoList(listId);
        todoList.stream().forEach((t) -> {
            log.info(t.getTodoTitle());
            todoService.updateTodoTitle(t.getTodoId(), newTodoTitle);
        });

        //then todoId를 이용해서 찾은 엔티티는 수정한 todoTitle로 변경되어야한다
        todoService.getTodoList(listId).forEach(e -> {
            log.info(e.getTodoTitle());
            Assertions.assertThat(e.getTodoTitle()).isEqualTo(newTodoTitle);
        });
    }

    @Test
    @DisplayName("Todo 완료하기")
    void todoDoneTest() {
        //given 엔티티를 생성하고
        Todo todo = todoService.createTodo("todo1", 100);
        //when toggle()메서드를 수행하면
        todoService.todoDoneToggle(todo.getTodoId());
        //then 엔티티.done의 값은 true가 된다
        Todo donedTodo = todoService.getTodo(todo.getTodoId());
        Assertions.assertThat(donedTodo.getDone()).isTrue();
    }

    @Test
    @DisplayName("Todo 완료 취소 하기")
    void todoUnDoneTest() {
        //given 엔티티를 생성하고
        Todo todo = todoService.createTodo("todo1", 100);
        //when toggle()메서드를 두 번 수행하면
        todoService.todoDoneToggle(todo.getTodoId());
        //then 실행후의 엔티티.done의 값은 각각 true, false가 된다
        Todo donedTodo = todoService.getTodo(todo.getTodoId());
        Assertions.assertThat(donedTodo.getDone()).isTrue();
        todoService.todoDoneToggle(todo.getTodoId());

        Todo unDonedTodo = todoService.getTodo(todo.getTodoId());
        Assertions.assertThat(unDonedTodo.getDone()).isFalse();
    }

    @Test
    @DisplayName("Todo 삭제하기")
    void todoDeleteTest() {
        //given todoList가 주어지고
        int listId = 123123;
        Todo todo1 = todoService.createTodo("todoRemoveTest", listId);
        Todo todo2 = todoService.createTodo("todoRemoveTest", listId);
        Todo todo3 = todoService.createTodo("todoRemoveTest", listId);
        Todo todo4 = todoService.createTodo("todoRemoveTest", listId);

        //when todo를 하나 삭제하면
        List<Todo> todoList = todoService.getTodoList(listId);
        todoService.deleteTodo(todoService.getTodoList(listId).get(0).getTodoId());
        //then todoList의 size는 1 줄어든다.
        Assertions.assertThat(todoService.getTodoList(listId).size()).isEqualTo(todoList.size() - 1);
    }

    /**
     * 리스트 아이디 listId에 n개 Todo를 생성해주는 메서드
     */
    private void createTestTodoListDate(int number, Integer listId) {
        for (int i = 0; i < number; i++) {
            String title = "newTodo" + (i + 1);
            todoService.createTodo(title, listId);
            log.info("todo : {}생성 완료", title);
        }
    }
}
