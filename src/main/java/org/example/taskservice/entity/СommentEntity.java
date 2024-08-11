package org.example.taskservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

/** Cущность комментария  */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "comments")
public class СommentEntity {
    /** Номер комментария  */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    /** Содержание комментария   */
    String comment;
    /** К какой задаче комментарий  */
    @ManyToOne
    Task task;
    /** Автор комментария  */
    @ManyToOne
    UserEntity author;
}
