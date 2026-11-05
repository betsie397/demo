package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_instruments")
@IdClass(UserInstrumentId.class)
public class UserInstrument {

    @Id
    @Column(name = "user_id")
    private int userId;

    @Id
    @Column(name = "instrument_id")
    private int instrumentId;

    protected UserInstrument() {}

    public void UserInstrument(int userId, int instrumentId) {
        this.userId = userId;
        this.instrumentId = instrumentId;
    }

    public int getUserId(){
        return userId;
    }

    public int getInstrumentId(){
        return instrumentId;
    }

}