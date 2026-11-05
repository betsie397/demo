package com.example.demo.repository;

import com.example.demo.entity.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface InstrumentRepository extends JpaRepository<Instrument, Integer> {
    Optional<Instrument> findByInstrumentName(String instrumentName);
}