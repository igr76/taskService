package org.example.taskservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 *  DTO для создания {@link org.example.taskservice.entity.Task} сущности
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GreatTaskDto {
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
