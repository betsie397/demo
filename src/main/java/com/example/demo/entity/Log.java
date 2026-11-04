package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "practice_log")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private int ID;

    @Column(name = "user_id", nullable = false)
    private int user_id;

    @Column(name = "practice_date", nullable = false)
    private String date;

    @Column(name = "duration", nullable = false)
    private String duration;

    @Column(name = "instrument_id", nullable = false)
    private String instrument;

    @Column(name = "scales")
    private Boolean scales;

    @Column(name = "exercises")
    private Boolean exercises;

    @Column(name = "pieces")
    private Boolean pieces;

    @Column(name = "description")
    private String description;

    @Column(name = "date_entered")
    private String dateEntered;

    public Log(){
        this.dateEntered = LocalDate.now().toString();
    }

    public Log(String date, String duration, String instrument, Boolean scales, Boolean exercises, Boolean pieces, String description, LocalDate dateEntered){
        this.date = date;
        this.duration = duration;
        this.instrument = instrument;
        this.scales = scales;
        this.exercises = exercises;
        this.pieces = pieces;
        this.description = description;
        this.dateEntered = LocalDate.now().toString();
    }

    public void setId(int Id){
        this.ID = Id + 1;
    }
    public void setDate(String date){
        this.date = date;
    }
    public void setDuration(String duration){
        this.duration = duration;
    }
    public void setInstrument(String instrument){
        this.instrument = instrument;
    }
    public void setScales(Boolean scales){
        this.scales = scales;
    }
    public void setExercises(Boolean exercises){
        this.exercises = exercises;
    }
    public void setPieces(Boolean pieces){
        this.pieces = pieces;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public int getId(){
        return ID;
    }
    public String getDate(){
        return date;
    }
    public String getDuration(){
        return duration;
    }
    public String getInstrument(){
        return instrument;
    }
    public Boolean getScales(){
        return scales;
    }
    public Boolean getExercises(){
        return exercises;
    }
    public Boolean getPieces(){
        return pieces;
    }
    public String getDescription(){
        return description;
    }
    public String getDateEntered(){
        return dateEntered;
    }
    
}
