package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    ScheduleResponseDto saveSchedule(Schedule schedule);

    List<ScheduleResponseDto> findAllSchedules();

    // 사용자 이름과 수정일 기반 검색 기능
    Optional<Schedule> findScheduleByuserName(String userName);
    Optional<Schedule> findScheduleBymodifyDate(Date modifyDate);
    Optional<Schedule> findScheduleByuserNameAndmodifyDate(String userName,Date modifyDate);
    Optional<Schedule> findScheduleById(Long scheduleId);

    int updateSchedule(Long id, String title, String contents);

    int updateTitle(Long id, String title);

    int deleteSchedule(Long id);

    Schedule findMemoByIdOrElseThrow(Long ScheduleId);

}
