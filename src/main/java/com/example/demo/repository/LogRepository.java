package com.example.demo.repository;

import com.example.demo.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface LogRepository extends JpaRepository<Log, Integer> {
    List<Log> findByUserId(int userId);
}