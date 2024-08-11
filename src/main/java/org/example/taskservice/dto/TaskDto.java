package org.example.taskservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 *  DTO для {@link org.example.taskservice.entity.Task} сущности
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskDto {
    int id;
    /** Заголовок задачи  */
    String heading;
    /** Описание задачи  */
    String description;
    /** Статус задачи  */
    Status status;
    /** Автор задачи id */
    long author;
    /** Исполнитель задачи id */
    long executor;
}
 //  {"id":"1", "heading":"head" ,"description":"head",
//  "status":"Status.IN_PROGRESS", "priority":"Priority.AVERAGE" ,"author":"head", "executor":"head"}