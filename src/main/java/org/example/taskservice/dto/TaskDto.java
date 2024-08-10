package org.example.taskservice.dto;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import org.example.taskservice.entity.UserEntity;

import java.util.List;

public class TaskDto {
    Integer id;
    String task;

    UserEntity author;

    UserEntity executor;

    List<String> comment;
    Status status;
}
