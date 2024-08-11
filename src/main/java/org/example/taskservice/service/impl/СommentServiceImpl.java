package org.example.taskservice.service.impl;


import org.example.taskservice.dto.СommentDto;
import org.example.taskservice.entity.СommentEntity;
import org.example.taskservice.exception.ElemNotFound;
import org.example.taskservice.mapper.СommentMapper;
import org.example.taskservice.repository.СommentRepository;
import org.example.taskservice.service.СommentService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/** Реализация сервиса комментариев*/
@Service
public class СommentServiceImpl implements СommentService {
    private  СommentRepository commentRepository;
    private  СommentMapper commentMapper;

    public СommentServiceImpl(СommentRepository commentRepository, СommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public List<СommentDto> getAllСommentsOfTask(int id) {
        СommentMapper commentMapper = null;
        return commentMapper.toCcommentListDto(commentRepository.findAllById(Collections.singleton(id)));
    }

    @Override
    public СommentDto greatСomment(СommentDto commentDto) {
        commentRepository.save(commentMapper.toEntity(commentDto));
        return commentDto;
    }

    @Override
    public void deleteСomment(int id) {
        СommentEntity commentEntity= new СommentEntity();
        commentEntity = commentRepository.findById(id).orElseThrow(()->
                new ElemNotFound("Такой задачи не существует"));
        commentRepository.delete(commentEntity);
    }
}
