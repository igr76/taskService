package org.example.taskservice.repository;

import org.example.taskservice.entity.СommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/** Репозиторий комментариев */
@Repository
public interface СommentRepository extends JpaRepository<СommentEntity,Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM comments WHERE task_id = :id ORDER BY id")
    List<СommentEntity> findByTaskId(int id);
    @Query(nativeQuery = true, value = "DELETE FROM comments WHERE task_id = :id ")
    void deleteAllFromTask(int id);
}
