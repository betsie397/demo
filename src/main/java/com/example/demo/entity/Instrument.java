package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "instruments")
public class Instrument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instrument_id")
    private Integer id;

    @Column(name = "instrument_name", nullable = false, length = 255)
    private String instrumentName;


    protected Instrument() {}

    public Instrument(String instrumentName) {
        this.instrumentName = instrumentName;
    }

    public String getInstrumentName(){
        return instrumentName;
    }

}