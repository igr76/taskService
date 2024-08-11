package org.example.taskservice.service;

import org.example.taskservice.dto.UserDto;
import org.example.taskservice.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

/** Сервис пользователей*/
public interface UserService {
    /** Получить данные пользователя*/
    UserDto getUser(String login/*, Authentication authentication*/);
    /** Обновить данные пользователя*/
    UserDto updateUser(UserDto newUserDto/*, Authentication authentication*/);
    /** Удалить данные пользователя*/
    void deleteUser(String login/*, Authentication authentication*/);
    /** Создать пользователя*/
    UserDto greateUser(UserDto userDto/*, Authentication authentication*/);
    /** Получить данные пользователя ро логину*/
    UserDto loadUserByName(String login);

    UserEntity getByLogin(String login);
}
