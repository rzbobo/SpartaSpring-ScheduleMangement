package com.example.schedulemanagement.controller;

import com.example.schedulemanagement.dto.ScheduleRequestDto;
import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/scheudle")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // 일정 생성(저장)
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto dto) {

        return new ResponseEntity<>(scheduleService.saveSchedule(dto), HttpStatus.CREATED);
    }

    // 일정 전체 조회
    @GetMapping
    public List<ScheduleResponseDto> getAllSchedules() {
        return scheduleService.findAllSchedules();
    }

    @GetMapping("/{username}")
    public ResponseEntity<ScheduleResponseDto> findScheduleByUserName(@PathVariable String username) {
        return new ResponseEntity<>(scheduleService.findScheduleByuserName(username), HttpStatus.OK);
    }

    @GetMapping("/{modifyDate}")
    public ResponseEntity<ScheduleResponseDto> findScheduleByModifyDate(@PathVariable Date modifyDate) {
        return new ResponseEntity<>(scheduleService.findScheduleBymodifyDate(modifyDate), HttpStatus.OK);
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleRequestDto requestDto)
    {
        return new ResponseEntity<>(scheduleService.updateSchedule(scheduleId,requestDto.getTitle(), requestDto.getContents()), HttpStatus.OK);
    }

    @DeleteMapping("/{scheduleId")
    public ResponseEntity<ScheduleResponseDto> deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
