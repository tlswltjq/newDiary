package org.example.newdiary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer todoId;
    @Column
    String todoTitle;
    @Column
    Boolean done;
    @Column
    Integer listId; //사용자가 여러 todoList를 구성할 수 있도록 todoList의 식별자 필드 추가함
    public void toggle() {
        this.done = !this.done;
    }

    public Todo updateTitle(String newTitle){
        this.todoTitle = newTitle;
        return this;
    }
}
