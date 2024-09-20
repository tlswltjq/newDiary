package org.example.newdiary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    @Column
    private Boolean isDone;

    @ManyToOne
    @JoinColumn(name = "todo_list_id")
    private TodoList todoList;

    public Todo done() {
        this.isDone = true;
        return this;
    }

    public Todo unDone() {
        this.isDone = false;
        return this;
    }

    public Todo updateDescription(String newDescription) {
        this.description = newDescription;
        return this;
    }
}