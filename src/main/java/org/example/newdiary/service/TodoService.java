package org.example.newdiary.service;

import lombok.RequiredArgsConstructor;
import org.example.newdiary.repository.TodoRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
}
