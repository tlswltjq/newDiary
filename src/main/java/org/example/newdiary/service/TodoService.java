package org.example.newdiary.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.newdiary.repository.TodoListRepository;
import org.example.newdiary.repository.TodoRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final TodoListRepository todoListRepository;

}
