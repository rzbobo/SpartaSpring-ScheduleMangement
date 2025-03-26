package com.example.schedulemanagement.service;

import com.example.schedulemanagement.dto.ScheduleRequestDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }



    // 일정 생성(저장)
    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto dto) {
        return null;
    }

    // 일정 전체 조회 (다음 조건을 바탕으로 등록된 일정 목록을 전부 조회)
    // 조건 중 한 가지만을 충족하거나, 둘 다 충족을 하지 않을 수도, 두 가지를 모두 충족할 수도 있음
    // 수정일 (형식 : YYYY-MM-DD) - 수정일 기준 내림차순으로 정렬하여 조회
    // 작성자명
    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        return List.of();
    }

    // 작성자명
    @Override
    public ScheduleResponseDto findScheduleByuserName(String userName) {
        return null;
    }
    // 수정일 (형식 : YYYY-MM-DD) - 수정일 기준 내림차순으로 정렬하여 조회
    @Override
    public ScheduleResponseDto findScheduleBymodifyDate(Date modifyDate) {
        return null;
    }

    // 선택한 일정 세부 조회
    @Override
    public ScheduleResponseDto findScheduleByScheduleId(long ScheduleId) {
        return null;
    }

    // 선택한 일정 수정하기
    @Override
    public ScheduleResponseDto updateSchedule(long ScheduleId, String title, String contents) {
        return null;
    }

    // 선택한 일정 제목 수정
    @Override
    public ScheduleResponseDto updateTitle(long ScheduleId, String title, String contents) {
        return null;
    }

    // 선택한 일정 삭제
    @Override
    public void deleteSchedule(long ScheduleId) {


    }
}
