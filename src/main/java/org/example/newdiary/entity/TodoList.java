package org.example.newdiary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "todo_list")
public class TodoList extends Do{

    @Column
    private String name;

    @OneToMany(mappedBy = "todoList", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    List<Todo> todoList = new ArrayList<>();

    public TodoList updateName(String newName) {
        this.name = newName;
        return this;
    }
    //todo : 이후 사용자 아이디 추가 필요
}
