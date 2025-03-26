package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.entity.User;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class JdbcTemplateScheduleRepository implements ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override // 생성
    public ScheduleResponseDto saveSchedule(Schedule schedule, User userName) {
        // INSERT Query를 직접 작성하지 않아도 된다.
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("scheduleId");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", schedule.getTitle());
        parameters.put("contents", schedule.getContents());
        parameters.put("createDate", schedule.getCreateDate());
        parameters.put("modifyDate", schedule.getModifyDate());

        // 저장 후 생성된 key값을 Number 타입으로 반환하는 메서드
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ScheduleResponseDto(key.longValue(), schedule.getTitle(), schedule.getContents());
    }


    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        return jdbcTemplate.query("select * from schedule", scheduleRowMapper());
    }

    private RowMapper<ScheduleResponseDto> scheduleRowMapper() {
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("scheduleId"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getDate("createDate"),
                        rs.getDate("modifyDate")
                );
            }

        };
    }

    // 사용자 이름기반 조회
    @Override
    public Optional<Schedule> findScheduleByuserName(String userName) {
        String sql = "SELECT schedule.scheduleId, schedule.title, schedule.contents, schedule.createDate, schedule.modifyDate " +
                "FROM schedule" +
                "JOIN user u ON s.userId = u.userId " +
                "WHERE u.username = ? OR s.modifyDate = ?";

        List<Schedule> result = jdbcTemplate.query(sql, scheduleRowMapperV2(), userName);
        return Optional.empty();
    }

    private RowMapper<Schedule> scheduleRowMapperV2() {
        return null;
    }

    @Override
    public Optional<Schedule> findScheduleBymodifyDate(Date modifyDate) {
        return Optional.empty();
    }

    @Override
    public Optional<Schedule> findScheduleByuserNameAndmodifyDate(String userName, Date modifyDate) {
        return Optional.empty();
    }

    @Override
    public Optional<Schedule> findScheduleById(Long scheduleId) {
        return Optional.empty();
    }

    @Override
    public int updateSchedule(Long id, String title, String contents) {
        return 0;
    }

    @Override
    public int updateTitle(Long id, String title) {
        return 0;
    }

    @Override
    public int deleteSchedule(Long id) {
        return 0;
    }

    @Override
    public Schedule findMemoByIdOrElseThrow(Long ScheduleId) {
        return null;
    }
}