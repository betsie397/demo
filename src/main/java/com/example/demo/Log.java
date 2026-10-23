package com.example.demo;

import java.time.LocalDate;

public class Log {
    private int ID;
    private String date;
    private String duration;
    private String instrument;
    private String scales;
    private String exercises;
    private String pieces;
    private String description;
    private String dateEntered;

    public Log(){
        this.dateEntered = LocalDate.now().toString();
    }

    public Log(int Id, String date, String duration, String instrument, String scales, String exercises, String pieces, String description){
        this.ID = Id;
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
    public void setScales(String scales){
        this.scales = scales;
    }
    public void setExercises(String exercises){
        this.exercises = exercises;
    }
    public void setPieces(String pieces){
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
    public String getScales(){
        return scales;
    }
    public String getExercises(){
        return exercises;
    }
    public String getPieces(){
        return pieces;
    }
    public String getDescription(){
        return description;
    }
    public String getDateEntered(){
        return dateEntered;
    }
    
}
