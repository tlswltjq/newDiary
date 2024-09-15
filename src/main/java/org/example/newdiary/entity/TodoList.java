package org.example.newdiary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoList {
    //투두리스트 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer listId;
    //투두리스트 명
    @Column
    String listName;
    @OneToMany(mappedBy = "listId", cascade = CascadeType.ALL)
    List<Todo> todos;
}
