package com.example.schedulemanagement.dto;

import com.example.schedulemanagement.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {
    private String userName;
    private String password;

    public UserDto(User user) {
        this.userName = getUserName();
    }
}
