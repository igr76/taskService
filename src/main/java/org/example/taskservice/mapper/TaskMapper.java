package org.example.taskservice.mapper;


import org.example.taskservice.dto.GreatTaskDto;
import org.example.taskservice.dto.TaskDto;
import org.example.taskservice.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * маппер для {@link Task} готовый dto {@link TaskDto}
 */
@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(target = "author.id", source = "author")
    @Mapping(target = "executor.id", source = "executor")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "priority", source = "priority")
    @Mapping(target = "id", ignore = true)
    Task toEntity(GreatTaskDto greatTaskDto);
    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "executor", source = "executor.id")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "priority", source = "priority")
    TaskDto toDTO(Task task);

    List<TaskDto> toListTaskDto(List<Task> taskList);
}
