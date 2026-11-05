package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

public class UserInstrumentId implements Serializable {
    private int userId;
    private int instrumentId;

    public UserInstrumentId() {}

    public UserInstrumentId(int userId, int instrumentId) {
        this.userId = userId;
        this.instrumentId = instrumentId;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof UserInstrumentId that)) return false;
        return userId == that.userId && instrumentId == that.instrumentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, instrumentId);
    }
}