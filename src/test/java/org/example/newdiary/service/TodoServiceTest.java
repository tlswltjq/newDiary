package org.example.newdiary.service;

import org.assertj.core.api.Assertions;
import org.example.newdiary.Util;
import org.example.newdiary.entity.Todo;
import org.example.newdiary.entity.TodoList;
import org.example.newdiary.event.NewActivityEvent;
import org.example.newdiary.repository.TodoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Todo 관련 테스트")
@ExtendWith(MockitoExtension.class)
class TodoServiceTest {
    private Util util = new Util();
    @Mock
    private TodoRepository todoRepository;

    @Mock
    private TodoListService todoListService;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private TodoService todoService;
    private Todo todo;
    private TodoList todoList;

    @DisplayName("description이 주어지면 새 Todo를 생성할 수 있다.")
    @Test
    public void todoCreateTest() {
        String description = "Test Todo";
        Long listId = 1L;
        todo = util.createTodoEntity(description, listId);
        todoList = todo.getTodoList();

        when(todoListService.getTodoList(listId)).thenReturn(todoList);
        when(todoRepository.save(any(Todo.class))).thenReturn(todo);

        Todo result = todoService.createTodo(description, listId);

        verify(todoListService).getTodoList(listId);
        verify(eventPublisher).publishEvent(any(NewActivityEvent.class));
        verify(todoRepository).save(any(Todo.class));

        Assertions.assertThat(result.getDescription()).isEqualTo(description);
        Assertions.assertThat(result.getIsDone()).isFalse();
        Assertions.assertThat(result.getTodoList().getId()).isEqualTo(listId);
    }

    @DisplayName("Todo의 완료 상태를 완료함(true)으로 변경할 수 있다. ")
    @Test
    public void todoDoneTest() {
        String description = "Test Todo";
        Long listId = 1L;
        todo = util.createTodoEntity(description, listId);

        when(todoRepository.save(any(Todo.class))).thenReturn(todo);
        when(todoRepository.findById(1L)).thenReturn(Optional.ofNullable(todo));

        Todo result = todoService.doneTodo(todo.getId());

        verify(eventPublisher).publishEvent(any(NewActivityEvent.class));
        verify(todoRepository).save(any(Todo.class));

        Assertions.assertThat(result.getIsDone()).isTrue();
    }

    @DisplayName("Todo의 완료 상태를 미 완료함(false)으로 변경할 수 있다. ")
    @Test
    public void todoUnDoneTest() {
        String description = "Test Todo";
        Long listId = 1L;
        todo = util.createTodoEntity(description, listId);

        when(todoRepository.save(any(Todo.class))).thenReturn(todo);
        when(todoRepository.findById(1L)).thenReturn(Optional.ofNullable(todo));

        Todo result = todoService.unDoneTodo(todo.getId());

        verify(eventPublisher).publishEvent(any(NewActivityEvent.class));
        verify(todoRepository).save(any(Todo.class));

        Assertions.assertThat(result.getIsDone()).isFalse();
    }

    @DisplayName("Todo의 설명을 변경할 수 있다.")
    @Test
    public void todoUpdateTest() {
        String description = "Test Todo";
        String newDescription = "updated";
        Long listId = 1L;
        todo = util.createTodoEntity(description, listId);

        when(todoRepository.findById(1L)).thenReturn(Optional.ofNullable(todo));
        when(todoRepository.save(any(Todo.class))).thenReturn(todo);

        Todo result = todoService.updateTodo(1L, newDescription);

        verify(eventPublisher).publishEvent(any(NewActivityEvent.class));
        verify(todoRepository).save(any(Todo.class));
        Assertions.assertThat(result.getDescription()).isEqualTo(newDescription);
    }

    @DisplayName("Todo를 Id를 이용해 삭제할 수 있다.")
    @Test
    public void todoDeleteTest() {
        String description = "Test Todo";
        Long listId = 1L;
        todo = util.createTodoEntity(description, listId);

        when(todoRepository.findById(1L)).thenReturn(Optional.ofNullable(todo));

        todoService.deleteTodo(1L);

        verify(eventPublisher).publishEvent(any(NewActivityEvent.class));
        verify(todoRepository).delete(any(Todo.class));
    }
}