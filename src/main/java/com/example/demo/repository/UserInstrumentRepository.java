package com.example.demo.repository;

import com.example.demo.entity.UserInstrument;
import com.example.demo.entity.UserInstrumentId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface UserInstrumentRepository extends JpaRepository<UserInstrument, UserInstrumentId> {
    List<UserInstrument> findByUserId(int userId);
}