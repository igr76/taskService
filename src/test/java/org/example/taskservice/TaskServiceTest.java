package org.example.taskservice;


import org.example.taskservice.dto.GreatTaskDto;
import org.example.taskservice.dto.Role;
import org.example.taskservice.dto.Status;
import org.example.taskservice.dto.TaskDto;
import org.example.taskservice.entity.Task;
import org.example.taskservice.entity.UserEntity;
import org.example.taskservice.exception.ElemNotFound;
import org.example.taskservice.mapper.TaskMapper;
import org.example.taskservice.repository.TaskRepository;
import org.example.taskservice.repository.UserRepository;
import org.example.taskservice.repository.СommentRepository;
import org.example.taskservice.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private TaskMapper taskMapper;
    @Mock
    private СommentRepository commentRepository;
    private UserRepository userRepository;
    @InjectMocks
    private TaskServiceImpl taskService = new TaskServiceImpl(taskRepository,taskMapper,commentRepository,userRepository);
    @Test
    void getTaskTest() {
        Task task=getTask();TaskDto taskDto=getTaskDto();

        when(taskRepository.findByHeading(any())).thenReturn(Optional.ofNullable(task));
        when(taskMapper.toDTO(any())).thenReturn(taskDto);
        assertThat(taskService.getTask(any())).isEqualTo(taskDto);
        verify(taskRepository, times(1)).findByHeading(any());
    }
    @Test
    void getTaskTestNegative() {
        when(taskRepository.findByHeading(any())).thenReturn(Optional.ofNullable(null));
        assertThatExceptionOfType(ElemNotFound.class).isThrownBy(() -> taskService.getTask("заголовок1"));
        verify(taskRepository, times(1)).findByHeading(any());
    }
    @Test
    void getTaskOfAuthorTest() {
        List<TaskDto> taskDtoList=new ArrayList<>();
        taskDtoList.add(getTaskDto());
       List<Task> taskList=new ArrayList<>();
        taskList.add(getTask());

        when(taskRepository.findAllByAuthor(anyString())).thenReturn(Optional.of(taskList));
        when(taskMapper.toListTaskDto(any())).thenReturn((List<TaskDto>) taskDtoList);
        assertThat(taskService.getTaskOfAuthor(anyString())).isEqualTo(taskDtoList);
        verify(taskRepository, times(1)).findAllByAuthor(any());
    }
    @Test
    void getTaskOfPriorityTest() {
        List<TaskDto> taskDtoList=new ArrayList<>();
        taskDtoList.add(getTaskDto());
        List<Task> taskList=new ArrayList<>();
        taskList.add(getTask());

        when(taskRepository.findAllByExecutor(anyString())).thenReturn(Optional.of(taskList));
        when(taskMapper.toListTaskDto(any())).thenReturn((List<TaskDto>) taskDtoList);
        assertThat(taskService.getTaskOfExecutor(anyString())).isEqualTo(taskDtoList);
        verify(taskRepository, times(1)).findAllByExecutor(any());
    }

       @Test
    void greatTaskTest() {
           GreatTaskDto taskDto=getGreatTaskDto();

       // when(taskRepository.findByHeading(any())).thenReturn(null);
           taskService.greatTask(taskDto);
        verify(taskRepository, times(1)).findByHeading(any());
    }
    @Test
    void greatTaskTestNegative() {
        Task task=getTask();GreatTaskDto taskDto=getGreatTaskDto();

        when(taskRepository.findByHeading(any())).thenReturn(Optional.ofNullable(task));
        assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() -> taskService.greatTask(taskDto));
        verify(taskRepository, times(1)).findByHeading(any());
    }
    @Test
    void updateTaskTest() {
        Task task=getTask();TaskDto taskDto=getTaskDto();
        UserEntity user=getUser();

        when(taskRepository.findByHeading(any())).thenReturn(Optional.ofNullable(task));
        when(taskRepository.save(any())).thenReturn(task);
        when(userRepository.findByName(any())).thenReturn(Optional.of(user));
        assertThat(taskService.updateTask(taskDto,any())).isEqualTo(taskDto);
        verify(taskRepository, times(1)).findByHeading(any());
    }
    @Test
    void updateTaskTestNegative() {
        TaskDto taskDto=getTaskDto();UserEntity user=getUser();

        when(taskRepository.findByHeading(any())).thenReturn(Optional.ofNullable(null));
        when(userRepository.findByName(any())).thenReturn(Optional.of(user));
        assertThatExceptionOfType(ElemNotFound.class).isThrownBy(() -> taskService.updateTask(taskDto,any()));
        verify(taskRepository, times(1)).findByHeading(any());
    }
    @Test
    void deleteTaskTest() {
        Task task=getTask();

        when(taskRepository.findByHeading(any())).thenReturn(Optional.ofNullable(task));
        doNothing().when(commentRepository).deleteAllFromTask(1);
        taskService.deleteTask("login",any());
        verify(taskRepository, times(1)).findByHeading(any());
    }
    @Test
    void deleteTaskTestNegative() {
        TaskDto taskDto=getTaskDto();

        when(taskRepository.findByHeading(any())).thenReturn(Optional.ofNullable(null));
        assertThatExceptionOfType(ElemNotFound.class).isThrownBy(() -> taskService.deleteTask("заголовок",any()));
        verify(taskRepository, times(1)).findByHeading(any());
    }
    private Task getTask() {
        Task task = new Task(1,"заголовок","описание", Status.IN_PROGRESS, getUser(),getUser());
        return task;
    }

    private TaskDto getTaskDto() {
        TaskDto taskDto=new TaskDto(1,"заголовок","описание", Status.IN_PROGRESS, 1L,1L);
        return taskDto;
    }
    private GreatTaskDto getGreatTaskDto() {
        GreatTaskDto greatTaskDto=new GreatTaskDto("заголовок","описание", Status.IN_PROGRESS, 1L,1L);
        return greatTaskDto;
    }
    private UserEntity getUser() {
        UserEntity user =new UserEntity(1,"login","1111",
                "name1", Role.ADMIN);
        return user;
    }

}
