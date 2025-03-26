package com.example.schedulemanagement.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class ScheduleRequestDto {

    private long scheduleId;
    private String title;
    private String contents;
    private Date createDate;
    private Date modifyDate;

}