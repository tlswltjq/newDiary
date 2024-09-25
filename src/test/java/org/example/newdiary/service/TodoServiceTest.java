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
import java.util.NoSuchElementException;

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
        //given 삭제할 Todo의 Id가 주어지고
        TodoList todoList = todoListService.createTodoList("testList");
        Todo todo = todoService.createTodo("testTodo", todoList.getId());

        //when Id를 이용해 삭제하면
        todoService.deleteTodo(todo.getId());

        //then 검색된 리스트의 todos.size()는 0이다.
        TodoList updatedTodoList = todoListService.getTodoList(todoList.getId());
        List<Todo> result = updatedTodoList.getTodoList();
        Assertions.assertThat(result.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("TodoList의 이름을 변경할 수 있다.")
    void updateTodoListTitle() {
        //given TodoList와 새 이름이 주어지고
        TodoList todoList = todoListService.createTodoList("before");
        String newName = "after";

        //when TodoList의 이름을 변경하고 다시 검색하면
        TodoList updated = todoListService.updateTodoListName(newName, todoList.getId());

        //then 변경된 이름을 확인할 수 있다.
        Assertions.assertThat(updated).isNotNull();
        Assertions.assertThat(updated.getName()).isEqualTo(newName);
    }

    @Test
    @DisplayName("TodoList를 삭제할 수 있다.")
    void deleteTodoList() {
        //given 삭제할 TodoList의 id가 주어지고
        TodoList todoList = todoListService.createTodoList("list");
        Todo todo1 = todoService.createTodo("todo1", todoList.getId());
        Todo todo2 = todoService.createTodo("todo2", todoList.getId());
        Todo todo3 = todoService.createTodo("todo3", todoList.getId());


        //when id를 이용해 삭제를 하면
        todoListService.deleteTodoList(todoList.getId());

        //then 포함된 Todo와 함께 TodoList가 삭제된다.
        Assertions.assertThatThrownBy(() -> todoListService.getTodoList(todoList.getId()))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("Wrong ListId");
        Assertions.assertThatThrownBy(() -> todoService.getTodo(todo1.getId()))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("Wrong TodoId");
        Assertions.assertThatThrownBy(() -> todoService.getTodo(todo2.getId()))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("Wrong TodoId");
        Assertions.assertThatThrownBy(() -> todoService.getTodo(todo3.getId()))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("Wrong TodoId");

    }

    @Test
    @DisplayName("Todo를 TodoList에 추가할 수 있다.")
    void addTodo(){
        //given TodoList와 추가할 Todo의 Description이 주어지고
        TodoList todoList = todoListService.createTodoList("testlist");
        String description = "newTodo";
        //when TodoList의 id를 이용해 주가하고 TodoList를 검색하면
        //then 추가한 크기의 TodoList가 반환된다.
        todoService.createTodo(description, todoList.getId());
        TodoList update1 = todoListService.getTodoList(todoList.getId());
        Assertions.assertThat(update1.getTodoList().size()).isEqualTo(1);
        todoService.createTodo(description, todoList.getId());
        todoService.createTodo(description, todoList.getId());
        todoService.createTodo(description, todoList.getId());
        TodoList update2 = todoListService.getTodoList(todoList.getId());
        Assertions.assertThat(update2.getTodoList().size()).isEqualTo(4);

    }
}