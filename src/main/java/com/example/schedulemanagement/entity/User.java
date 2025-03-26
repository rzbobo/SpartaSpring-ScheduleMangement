package com.example.schedulemanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class User {
    private long userId;
    private String userName;
    private String password;

    public User(Long userId,String userName) {
        this.userId = userId;
        this.userName =userName;
    }
}
