package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "user_name", nullable = false, length = 45)
    private String name;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "color", nullable = false)
    private int color = 220;

    protected User() {}

    public User(String name, String email, String passwordHash) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public String getPasswordHash(){
        return passwordHash;
    }

    public int getId(){
        return id;
    }

    public int getColor(){
        return color;
    }

    public void setColor(int color){
        this.color = color;
    }

}