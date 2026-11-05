package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "practice_log")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private int id;

    @Column(name = "userId", nullable = false)
    private int userId;

    @Column(name = "practice_date", nullable = false)
    private String date;

    @Column(name = "duration", nullable = false)
    private String duration;

    @Column(name = "instrument_id", nullable = false)
    private int instrumentId;

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

    public Log(int id, int userId, String date, String duration, int instrumentId, Boolean scales, Boolean exercises, Boolean pieces, String description, LocalDate dateEntered){
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.duration = duration;
        this.instrumentId = instrumentId;
        this.scales = scales;
        this.exercises = exercises;
        this.pieces = pieces;
        this.description = description;
        this.dateEntered = LocalDate.now().toString();
    }

    public Log(String date, String duration, int instrumentId, Boolean scales, Boolean exercises, Boolean pieces, String description, LocalDate dateEntered){
        this.date = date;
        this.duration = duration;
        this.instrumentId = instrumentId;
        this.scales = scales;
        this.exercises = exercises;
        this.pieces = pieces;
        this.description = description;
        this.dateEntered = LocalDate.now().toString();
    }

    public void setId(int id){
        this.id = id;
    }
    public void setUserId(int userId){
        this.userId = userId;
    }
    public void setDate(String date){
        this.date = date;
    }
    public void setDuration(String duration){
        this.duration = duration;
    }
    public void setInstrument(int instrumentId){
        this.instrumentId = instrumentId;
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
        return id;
    }
    public int getUserId(){
        return userId;
    }
    public String getDate(){
        return date;
    }
    public String getDuration(){
        return duration;
    }
    public int getInstrumentId(){
        return instrumentId;
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
