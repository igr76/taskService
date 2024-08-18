package org.example.taskservice.service.impl;


import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.taskservice.dto.UserDto;
import org.example.taskservice.entity.UserEntity;
import org.example.taskservice.exception.ElemNotFound;
import org.example.taskservice.mapper.UserMapper;
import org.example.taskservice.repository.UserRepository;
import org.example.taskservice.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/** Реализация сервиса пользователей*/
@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
  private UserRepository userRepository;
  private UserMapper userMapper;

  public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  @Override
  public UserDto getUser(String login) {
    log.debug("Получить данные пользователя" );
    UserEntity user= new UserEntity();
    user= (UserEntity) userRepository.findByName( login).orElseThrow(()->
            new ElemNotFound("Такого пользователя не существует"));
    return userMapper.toDTO(user);
  }@Override
  public UserDto greateUser(UserDto userDto) {
    log.debug("Создать пользователя");
    if (userRepository.findByNameIsNotNull(userDto.getName())) {
      throw new UnsupportedOperationException("Такой пользователь уже существует");
    } else userRepository.save(userMapper.toEntity(userDto));
      return userDto;
  }

  @Override
  public UserDto loadUserByName(String login) {
    return userMapper.toDTO((UserEntity) userRepository.findByName(login).orElseThrow(ElemNotFound::new));
  }

  @Override
  public UserEntity getByLogin(String login) {
    return (UserEntity) userRepository.findByName(login).orElseThrow(ElemNotFound::new);
  }

  @Override
  public UserDto updateUser(UserDto newUserDto) {
    log.debug("Обновить данные пользователя");
    UserEntity user= new UserEntity();
    user= userRepository.findByEmail(newUserDto.getEmail()).orElseThrow(()->
            new ElemNotFound("Такого пользователя не существует"));
    user=userMapper.toEntity(newUserDto);
    userRepository.save(user);
    return newUserDto;
  }

  @Override
  public void deleteUser(String login) {
    log.debug("Удалить пользователя");
    UserEntity user= new UserEntity();
    user= userRepository.findByEmail(login).orElseThrow(()->
            new ElemNotFound("Такого пользователя не существует"));
    userRepository.delete(user);
  }


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username);
  }
}
