package com.example.demo.repository;

import com.example.demo.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LogRepository extends JpaRepository<Log, Integer> {

}