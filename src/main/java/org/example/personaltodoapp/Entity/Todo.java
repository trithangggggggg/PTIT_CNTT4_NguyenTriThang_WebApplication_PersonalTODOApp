package org.example.personaltodoapp.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Content ko dc trong")
    @Column(name = "content")
    private String content;

    @NotNull(message = "Due date ko dc trong")
    @FutureOrPresent(message = "Due date ko dc qua khu")
    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @NotNull(message = "Status ko dc trong")
//    @Enumerated(EnumType.STRING)
    private EnumStatus status;

    @NotNull(message = "Priority ko dc trong")
//    @Enumerated(EnumType.STRING)
    private EnumPriority priority;
}