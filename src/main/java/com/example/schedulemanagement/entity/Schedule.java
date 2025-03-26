package com.example.schedulemanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;


@Getter
@AllArgsConstructor
public class Schedule {
    private long scheduleId;
    private String title;
    private String contents;
    private Date createDate;
    private Date modifyDate;


    public Schedule(String title, String contents, Date createDate, Date modifyDate) {

        this.title = title;
        this.contents = contents;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public void update(String title, String contents, Date modifyDate) {
        this.title = title;
        this.contents = contents;
        this.modifyDate = modifyDate;
    }


}
