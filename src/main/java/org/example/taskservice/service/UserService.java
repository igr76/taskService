package org.example.taskservice.service;


import lombok.NonNull;
import org.example.taskservice.dto.NewPassword;
import org.example.taskservice.dto.UserDto;
import org.example.taskservice.entity.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

/**
 * сервис пользователя
 */
public interface UserService {
  public UserEntity getByLogin(@NonNull String login);

  /**
   * получить пользователя
   */
  UserDto getUser(Authentication authentication);

  /**
   * обновить пользователя
   */
  UserDto updateUser(UserDto userDto, Authentication authentication) ;

  /**
   * удалить пользователя
   */
  void deleteUser(UserDto userDto, Authentication authentication) ;
  /**
   * установить новый пароль пользователя
   */
  NewPassword setPassword(NewPassword newPassword);


  /** найти пользователя по id */
  UserDto findById(int id, Authentication authentication);
  /** отправить сообщение другу */
  void messageOfFriend(int id,String message);
/** добавить пользователя в подписку */
 // void addSubscription(String friend, Authentication authentication);
}
