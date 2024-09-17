package org.example.newdiary.service;

import lombok.RequiredArgsConstructor;
import org.example.newdiary.repository.TodoListRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoListService {
    private final TodoListRepository todoListRepository;

}
