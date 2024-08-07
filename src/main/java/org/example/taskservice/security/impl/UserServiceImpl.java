package org.example.taskservice.security.impl;



import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.example.taskservice.dto.NewPassword;
import org.example.taskservice.dto.UserDto;
import org.example.taskservice.entity.UserEntity;
import org.example.taskservice.exception.ElemNotFound;
import org.example.taskservice.exception.SecurityAccessException;
import org.example.taskservice.mapper.UserMapper;
import org.example.taskservice.repository.UserRepository;
import org.example.taskservice.service.SecurityService;
import org.example.taskservice.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

/**
 * Сервис пользователей
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final SecurityService securityService;


  public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, SecurityService securityService) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
    this.securityService = securityService;
  }

  public UserEntity getByLogin(@NonNull String login) {
    return userRepository.findByName(login).orElseThrow(ElemNotFound::new);
  }
  /**
   * Получить данные пользователя
   */
  @Override
  public UserDto getUser(Authentication authentication) {
    log.info("getUser");
    if (!securityService.checkAuthorRoleFromAuthentication(authentication)){ new SecurityAccessException();}
    String nameEmail = authentication.getName();
    UserEntity userEntity = findEntityByEmail(nameEmail);
    return userMapper.toDTO(userEntity);
  }

  /**
   * Обновить данные пользователя
   */
  @Override
  public UserDto updateUser(UserDto newUserDto, Authentication authentication) {
    log.info("updateUser");
    if (!securityService.checkAuthorRoleFromAuthentication(authentication)
    ||!securityService.isAuthorAuthenticated(newUserDto.getId(),authentication)){ new SecurityAccessException();}
    UserEntity user = userRepository.findByEmail(authentication.getName()).orElseThrow(ElemNotFound::new);
    newUserDto.setId(user.getId());

    userRepository.save(userMapper.toEntity(newUserDto));

    return newUserDto;
  }
  /**
   * Удалить данные пользователя
   */
  @Override
  public void deleteUser(UserDto userDto, Authentication authentication) {
    log.info("deleteUser");
    if (!securityService.checkAuthorRoleFromAuthentication(authentication)
            ||!securityService.isAuthorAuthenticated(userDto.getId(),authentication)){ new SecurityAccessException();}
    UserEntity user = userRepository.findByEmail(authentication.getName()).orElseThrow(ElemNotFound::new);
    userRepository.deleteById(user.getId());
  }

  /**
   * Установить пароль пользователя
   */
  @Override
  public NewPassword setPassword(NewPassword newPassword) {
    log.info("setPassword");
    return null;
  }



  /**
   * найти пользователя по id
   *
   * @param id id пользователя
   * @return пользователь
   */
  @Override
  public UserDto findById(int id,Authentication authentication) {
    log.info("findById");
    if (!securityService.checkAuthorRoleFromAuthentication(authentication)){ new SecurityAccessException();}
    return userMapper.toDTO(userRepository.findById(id).orElseThrow(ElemNotFound::new));
  }


  /**
   * отправить сообщение другу
   *
   * @param id  - номер пользователя
   * @param message  - сообщение другу
   * @return пользователь
   */
  @Override
  public void messageOfFriend(int id,String message) {
    UserEntity user = userRepository.findById(id).orElseThrow(ElemNotFound::new);
//    Collection<String> getMessage1 = user.getMessage();
//    getMessage1.add(message);
//    user.setMessage(getMessage1);
  }


  /**
   * добавить пользователя в подписку
   *
   * @param  authentication - логину пользователя
   * @param nameUser email - логину пользователя
   * @return пользователь
   */
//  public void addSubscription(String nameUser, Authentication authentication) {
//    UserEntity user = findEntityByEmail(authentication.getName());
//    int idFriend = (userRepository.findByName(nameUser).orElseThrow(ElemNotFound::new))
//            .getId();
//    Friend friend = new Friend();
//    friend.setUser1(user.getId());
//    friend.setUser2(idFriend);
//    friend.setStatus(StatusFriend.SUBSCRIPTION);
//    friendsRepository.save(friend);
//  }
//  /**
//   * Отписаться от пользователя
//   *
//   * @param  authentication - логину пользователя
//   * @param nameUser email - логину пользователя
//   * @return пользователь
//   */
//  public void deleteSubscription(String nameUser, Authentication authentication) {
//    UserEntity user = findEntityByEmail(authentication.getName());
//    int idFriend = (userRepository.findByName(nameUser).orElseThrow(ElemNotFound::new))
//            .getId();
//    Friend friend = friendsRepository.findByUser1AndUser2(user.getId(), idFriend).orElseThrow(ElemNotFound::new);
//    friendsRepository.delete(friend);
//  }
  /**
   * найти пользователя по email - логину
   *
   * @param email email - логину пользователя
   * @return пользователь
   */
  public UserEntity findEntityByEmail(String email) {
    log.info("qq");
    return userRepository.findByEmail(email).get();
  }


}
