package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.ScheduleRequestDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.dto.UserDto;

import java.util.Date;
import java.util.List;

public interface ScheduleService {

    ScheduleResponseDto saveSchedule(ScheduleRequestDto dto);

    // 전체일정조회
    List<ScheduleResponseDto> findAllSchedules();

    // modifyDate , userName
    ScheduleResponseDto findScheduleByUserNameOrModifyDate(String userName, Date modifyDate);

    ScheduleResponseDto findScheduleByUserNameAndModifyDate(String userName, Date modifyDate);

    ScheduleResponseDto updateSchedule(long ScheduleId, String userName, String contents);

    void deleteSchedule(long ScheduleId);
}
