package org.example.taskservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.taskservice.entity.СommentEntity;

/**
 *  DTO для {@link СommentEntity} сущности
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class СommentDto {
    /** Номер комментария  */
    int id;
    /** Содержание комментария   */
    String comment;
    /** К какой задаче комментарий  */
    int task;
    /** Автор комментария  */
    int author;
}
