package org.example.taskservice.mapper;


import org.example.taskservice.dto.СommentDto;
import org.example.taskservice.entity.СommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * маппер для {@link СommentEntity} готовый dto {@link СommentDto}
 */
@Mapper(componentModel = "spring")
public interface СommentMapper {
    @Mapping(target = "task.id", source = "task")
    @Mapping(target = "author.id", source = "author")
    @Mapping(target = "status", source = "status")
    СommentEntity toEntity(СommentDto commentDto);
    @Mapping(target = "task", source = "task.id")
    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "status", source = "status")
    СommentDto toDTO(СommentEntity comment);

    List<СommentDto> toCcommentListDto(List<СommentEntity> commentList);
}

