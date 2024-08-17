package org.example.taskservice.service;


import org.example.taskservice.dto.СommentDto;

import java.util.List;

/** Сервис комментариев*/
public interface СommentService {
    /** Получить все комментарии к задаче
     * @param id - номер задачи*/
    List<СommentDto> getAllСommentsOfTask(int id);
    /** Создать комментарий к задаче
     * @param commentDto - тело комментария*/
    СommentDto greatСomment(СommentDto commentDto);
    /** Удалить комментарий
     * @param id - номер комментария*/
    void deleteСomment(int id);

}
