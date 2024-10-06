package org.example.newdiary.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@MappedSuperclass
public class Do {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
