package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.entity.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    ScheduleResponseDto saveSchedule(Schedule schedule, User username);

    List<ScheduleResponseDto> findAllSchedules();

    // 사용자 이름과 수정일 기반 검색 기능 [OR]
    Optional<ScheduleResponseDto> findScheduleByuserNameOrModifyDate(String username,Date modifyDate);
    // 사용자 이름과 수정일 기반 검색 기능 [AND]
    Optional<ScheduleResponseDto> findScheduleByuserNameAndmodifyDate(String username,Date modifyDate);

    int updateSchedule(Long scheduleId, String userName, String contents);

    int deleteSchedule(Long scheduleId);
}
