package org.example.taskservice;


import org.example.taskservice.dto.Status;
import org.example.taskservice.dto.СommentDto;
import org.example.taskservice.entity.Task;
import org.example.taskservice.entity.СommentEntity;
import org.example.taskservice.mapper.СommentMapper;
import org.example.taskservice.repository.СommentRepository;
import org.example.taskservice.service.impl.СommentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class СommentServiceTest {
    @Mock
    private СommentRepository commentRepository;
    @Mock
    private СommentMapper commentMapper;
    @InjectMocks
    private СommentServiceImpl сommentService = new СommentServiceImpl(commentRepository,commentMapper);

    @Test
    void getAllСommentsOfTaskTest() {
        Collection<СommentEntity> entityList=new ArrayList<>();
        entityList.add(getСommentEntity());
        Collection<СommentDto> dtoList=new ArrayList<>();
        dtoList.add(getСommentDto());

        when(commentRepository.findByTaskId(1)).thenReturn((List<СommentEntity>) entityList);
        when(commentMapper.toCcommentListDto(any())).thenReturn((List<СommentDto>) dtoList);
        assertThat(сommentService.getAllСommentsOfTask(1)).isEqualTo(dtoList);
        verify(commentRepository, times(1)).findByTaskId(1);
    }
    @Test
    void greatСommentTest() {
        СommentEntity commentEntity=getСommentEntity();СommentDto commentDto=getСommentDto();
        assertThat(сommentService.greatСomment(commentDto)).isEqualTo(commentDto);
        verify(commentRepository, times(1)).save(any());
    }
    @Test
    void deleteСommentTest() {
        СommentEntity commentEntity=getСommentEntity();

        when(commentRepository.findById(any())).thenReturn(Optional.ofNullable(commentEntity));
        сommentService.deleteСomment(1);
        verify(commentRepository, times(1)).findById(any());
    }

    СommentEntity getСommentEntity() {
        СommentEntity commentEntity = new СommentEntity(1,"comment",getTask(),null);
        return commentEntity;
    }
    СommentDto getСommentDto() {
        СommentDto commentDto = new СommentDto(1,"comment",1,1);
        return commentDto;
    }
    private Task getTask() {
        Task task = new Task(1,"заголовок","описание", Status.IN_PROGRESS, null,null);
        return task;
    }
}
