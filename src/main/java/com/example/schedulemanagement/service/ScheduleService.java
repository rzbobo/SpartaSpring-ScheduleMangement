package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.ScheduleRequestDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;

import java.util.Date;
import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto saveSchedule(ScheduleRequestDto dto);

    // 전체일정조회
    List<ScheduleResponseDto> findAllSchedules();

    // modifyDate , userName
    ScheduleResponseDto findScheduleByuserName(String userName);

    ScheduleResponseDto findScheduleBymodifyDate(Date modifyDate);

    ScheduleResponseDto findScheduleByScheduleId(long ScheduleId);

    ScheduleResponseDto updateSchedule(long ScheduleId, String title, String contents);

    ScheduleResponseDto updateTitle(long ScheduleId, String title, String contents);

    void deleteSchedule(long ScheduleId);
}
