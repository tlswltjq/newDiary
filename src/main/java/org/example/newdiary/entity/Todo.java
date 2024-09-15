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
    @ManyToOne
    @JoinColumn(name = "list_id")
    TodoList listId;

    public void toggle() {
        this.done = !this.done;
    }

    public Todo updateTitle(String newTitle) {
        this.todoTitle = newTitle;
        return this;
    }
}
