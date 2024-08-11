package org.example.taskservice.repository;

import org.example.taskservice.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/** Репозиторий задач */
@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
    Optional<Task> findByHeading(String heading);
    Optional<List<Task>> findAllByExecutor(String priority);
    Optional<List<Task>> findAllByAuthor(String author);
    Optional<List<Task>> findAllById(Integer id);
    boolean findByHeadingIsNotNull(String heading);

}
