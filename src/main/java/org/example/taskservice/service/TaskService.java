package org.example.taskservice.service;


import org.example.taskservice.dto.GreatTaskDto;
import org.example.taskservice.dto.TaskDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

/** Сервис задач*/
@Service
public interface TaskService {
    /** Получить задачу по заголовку
     * @param heading - заголовок задачи*/
    TaskDto getTask(String heading);
    /** Получить все задачи  автора
     * @param author - автор задачи*/
    List<TaskDto> getTaskOfAuthor(String author);
    /** Получить все задачи  исполнителя
     * @param priority - исполнитель задачи*/
    List<TaskDto> getTaskOfExecutor(String priority);
    /** Получить все задачи*/
    List<TaskDto> getAllTasks();
    /** Создать задачу
     * @param greatTaskDto - тело задачи*/
    void greatTask(GreatTaskDto greatTaskDto);
    /** Обновить задачу
     * @param taskDto - тело задачи*/
    TaskDto updateTask(TaskDto taskDto, Authentication authentication);
    /** Изменить статус задачи
     * @param taskDto - тело задачи*/
    TaskDto updateStatusTask(TaskDto taskDto);
    /** Удалить задачу по заголовку
     * @param heading - заголовок задачи*/
    void deleteTask(String heading, Authentication authentication);

}
