package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.ScheduleRequestDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.dto.UserDto;
import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.entity.User;
import com.example.schedulemanagement.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }



    // 일정 생성(저장)
    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto dto) {

        Schedule schedule = new Schedule(dto.getTitle(),dto.getContents(), dto.getCreateDate(), dto.getModifyDate());
        User user = new User(dto.getUserId(), dto.getUserName());
        return new ScheduleResponseDto(schedule, user);
    }

    // 일정 전체 조회 (다음 조건을 바탕으로 등록된 일정 목록을 전부 조회)
    // 조건 중 한 가지만을 충족하거나, 둘 다 충족을 하지 않을 수도, 두 가지를 모두 충족할 수도 있음
    // 수정일 (형식 : YYYY-MM-DD) - 수정일 기준 내림차순으로 정렬하여 조회
    // 작성자명
    @Override
    public List<ScheduleResponseDto> findAllSchedules() {

        List<ScheduleResponseDto> allSchedules = scheduleRepository.findAllSchedules();

        return allSchedules;
    }

    // 작성자명
    @Override
    public ScheduleResponseDto findScheduleByUserNameOrModifyDate(String userName, Date modifyDate) {

        Optional<ScheduleResponseDto> schedule = scheduleRepository.findScheduleByuserNameOrModifyDate(userName, modifyDate);

        return new ScheduleResponseDto(schedule);
    }
    // 수정일 (형식 : YYYY-MM-DD) - 수정일 기준 내림차순으로 정렬하여 조회
    @Override
    public ScheduleResponseDto findScheduleByUserNameAndModifyDate(String userName, Date modifyDate) {

        return null;
    }



    // 선택한 일정 수정하기
    @Transactional
    @Override
    public ScheduleResponseDto updateSchedule(long ScheduleId, String userName, String contents) {
        // 필수값 검증
        if (userName == null || contents == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and content are required values.");
        }

        // 일정 수정
        int updatedRow = scheduleRepository.updateSchedule(scheduleId, userName, contents);
        // 수정된 row가 0개라면
        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data has been modified.");
        }

        // 수정된 메모 조회
        return new ScheduleResponseDto(scheduleRepository.findscheduleByScheduleId(scheduleId).get());
    }


    // 선택한 일정 삭제
    @Override
    public void deleteSchedule(long ScheduleId) {


    }
}
