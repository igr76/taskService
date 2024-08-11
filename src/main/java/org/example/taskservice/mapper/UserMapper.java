package org.example.taskservice.mapper;


import org.example.taskservice.dto.UserDto;
import org.example.taskservice.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

/**
 * маппер для {@link UserEntity} готовый dto {@link UserDto}
 */

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    UserEntity toEntity(UserDto userDto);


    UserDto toDTO(UserEntity userEntity);

    Collection<UserEntity> toEntityList(Collection<UserDto> userDTOS);

    Collection<UserDto> toDTOList(Collection<UserEntity> userEntities);
}
