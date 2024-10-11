package org.example.newdiary.service;

import org.assertj.core.api.Assertions;
import org.example.newdiary.Util;
import org.example.newdiary.entity.TodoList;
import org.example.newdiary.event.NewActivityEvent;
import org.example.newdiary.repository.TodoListRepository;
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

@DisplayName("TodoList 관련 테스트")
@ExtendWith(MockitoExtension.class)
class TodoListServiceTest {
    private Util util = new Util();
    @Mock
    private TodoListRepository todoListRepository;
    @Mock
    private ApplicationEventPublisher eventPublisher;
    @InjectMocks
    private TodoListService todoListService;
    private TodoList todoList;

    @Test
    @DisplayName("TodoList의 이름을 변경할 수 있다.")
    void updateTodoListTitle() {
        todoList = util.createTodoListEntity("testList");
        String newTitle = "newList";

        when(todoListRepository.save(any(TodoList.class))).thenReturn(todoList);
        when(todoListRepository.findById(1L)).thenReturn(Optional.ofNullable(todoList));

        TodoList result = todoListService.updateTodoListName(newTitle, todoList.getId());

        verify(eventPublisher).publishEvent(any(NewActivityEvent.class));
        verify(todoListRepository).save(any(TodoList.class));

        Assertions.assertThat(result.getName()).isEqualTo(newTitle);
    }

    @Test
    @DisplayName("TodoList를 삭제할 수 있다.")
    void deleteTodoList() {
        todoList = util.createTodoListEntity("testList");

        when(todoListRepository.findById(todoList.getId())).thenReturn(Optional.ofNullable(todoList));

        todoListService.deleteTodoList(todoList.getId());

        verify(eventPublisher).publishEvent(any(NewActivityEvent.class));
        verify(todoListRepository).delete(todoList);
    }
}
