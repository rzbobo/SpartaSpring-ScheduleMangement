package com.example.schedulemanagement.dto;

import com.example.schedulemanagement.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private long scheduleId;
    private String title;
    private String contents;
    private Date createDate;
    private Date modifyDate;

    public ScheduleResponseDto(Schedule schedule) {
        this.scheduleId = schedule.getScheduleId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.createDate = schedule.getCreateDate();
        this.modifyDate = schedule.getModifyDate();
    }

    public ScheduleResponseDto(long l, String title, String contents) {
    }
}
