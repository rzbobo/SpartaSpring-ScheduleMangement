package com.example.schedulemanagement.dto;

import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {

    private long scheduleId;
    private long userId;
    private String username;
    private String title;
    private String contents;
    private Date createDate;
    private Date modifyDate;
    

    public ScheduleResponseDto(Schedule schedule, User user) {
        this.scheduleId = schedule.getScheduleId();
        this.title = schedule.getTitle();
        this.userId = user.getUserId();
        this.username = user.getUserName();
        this.contents = schedule.getContents();
        this.createDate = schedule.getCreateDate();
        this.modifyDate = schedule.getModifyDate();
    }

}
