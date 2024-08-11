package org.example.taskservice.repository;

import org.example.taskservice.entity.TokenStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenStorageRepository extends JpaRepository<TokenStorage,String> {
}
