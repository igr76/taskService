package org.example.taskservice;


import org.example.taskservice.dto.Role;
import org.example.taskservice.dto.UserDto;
import org.example.taskservice.entity.UserEntity;
import org.example.taskservice.exception.ElemNotFound;
import org.example.taskservice.mapper.UserMapper;
import org.example.taskservice.repository.UserRepository;
import org.example.taskservice.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserServiceImpl userService = new UserServiceImpl(userRepository,userMapper);
    @Test
    void getUserTest() {
        UserEntity user = getUser();
        UserDto userDto = getUserDto();

        when(userRepository.findByName(any())).thenReturn(Optional.ofNullable(user));
        when(userMapper.toDTO(any())).thenReturn(userDto);
        assertThat(userService.getUser(any())).isEqualTo(userDto);
        verify(userRepository, times(1)).findByName(any());
    }
    @Test
    void getUserTestNegative() {
        when(userRepository.findByName(any())).thenReturn(Optional.ofNullable(null));
        assertThatExceptionOfType(ElemNotFound.class).isThrownBy(() -> userService.getUser("name"));
        verify(userRepository, times(1)).findByName(any());
    }
    @Test
    void greateUserTest() {
        UserEntity user = getUser();UserDto userDto = getUserDto();

      //  when(userRepository.findByLogin(any())).thenReturn(null);
        assertThat(userService.greateUser(userDto)).isEqualTo(userDto);
        verify(userRepository, times(1)).findByName(any());
    }
    @Test
    void greateUserTestNegative() {
        when(userRepository.findByName(any())).thenReturn(Optional.ofNullable(getUser()));
        assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() -> userService.greateUser(getUserDto()));
        verify(userRepository, times(1)).findByName(any());
    }
    @Test
    void updateUserTest() {
        UserEntity user = getUser();UserDto userDto = getUserDto();

        when(userRepository.findByName(any())).thenReturn(Optional.ofNullable(user));
        when(userMapper.toEntity(any())).thenReturn(user);
        when(userRepository.save(any())).thenReturn(user);
        assertThat(userService.updateUser(userDto)).isEqualTo(userDto);
        verify(userRepository, times(1)).findByName(any());
    }
    @Test
    void updateUserTestNegative() {
        when(userRepository.findByName(any())).thenReturn(Optional.ofNullable(null));
        assertThatExceptionOfType(ElemNotFound.class).isThrownBy(() -> userService.updateUser(getUserDto()));
        verify(userRepository, times(1)).findByName(any());
    }
    @Test
    void deleteUserTest() {
        UserEntity user = getUser();

        when(userRepository.findByName(any())).thenReturn(Optional.ofNullable(user));
        userService.deleteUser("login");;
        verify(userRepository, times(1)).findByName(any());
    }
    @Test
    void deleteUserTestNegative() {
        when(userRepository.findByName(any())).thenReturn(Optional.ofNullable(null));
        assertThatExceptionOfType(ElemNotFound.class).isThrownBy(() -> userService.deleteUser("name"));
        verify(userRepository, times(1)).findByName(any());
    }

    UserEntity getUser() {
        UserEntity user =new UserEntity(1,"Name","1111",
                "name1", Role.ADMIN);
        return user;
    }

    UserDto getUserDto() {
        UserDto userDto = new UserDto("login","1111",
                "name1");
        return userDto;
    }
}
