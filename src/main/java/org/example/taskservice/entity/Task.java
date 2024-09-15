package org.example.taskservice.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.taskservice.dto.Status;

/** Cущность задач  */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "tasks")
public class Task {
    /** номер задачи  */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    /** Заголовок задачи  */
    String heading;
    /** Описание задачи  */
    String description;
    /** Статус задачи  */
    Status status;
    /** Автор задачи  */
    @ManyToOne(fetch = FetchType.LAZY)
    UserEntity author;
    /** Исполнитель задачи  */
    @ManyToOne(fetch = FetchType.LAZY)
    UserEntity executor;
}
