package com.example.schedulemanagement.repository;

import com.example.schedulemanagement.dto.ScheduleResponseDto;
import com.example.schedulemanagement.dto.UserDto;
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
    public ScheduleResponseDto saveSchedule(Schedule schedule, User user) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);


        // user 먼저 넣기
        jdbcInsert.withTableName("user").usingGeneratedKeyColumns("userId");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("username", user.getUserName());
        parameters.put("password", user.getPassword());

        // userId값 받아오기
        Number userId = jdbcInsert.executeAndReturnKey(parameters);

        // 받아온 userId와 나머지 값을 schedule에 넣기
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("scheduleId");
        Map<String, Object> parameters2 = new HashMap<>();
        parameters2.put("userId",userId);
        parameters2.put("title", schedule.getTitle());
        parameters2.put("contents", schedule.getContents());
        parameters2.put("createDate", schedule.getCreateDate());

        //scheduleId 받아오기
        Number scheduleId = jdbcInsert.executeAndReturnKey(parameters2);

        return new ScheduleResponseDto(scheduleId.longValue(), userId.longValue(), user.getUserName(),schedule.getTitle(), schedule.getContents(), schedule.getCreateDate(), schedule.getModifyDate());
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
                        rs.getLong("userId"),
                        rs.getString("userName"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getDate("createDate"),
                        rs.getDate("modifyDate")
                );
            }

        };
    }

    // 사용자 이름, 수정일 기반 각각 조회
    @Override
    public Optional<ScheduleResponseDto> findScheduleByuserNameOrModifyDate(String userName, Date modifyDate) {
        String sql = "SELECT schedule.scheduleId, schedule.title, schedule.contents, schedule.createDate, schedule.modifyDate " +
                "FROM schedule" +
                "JOIN user ON schedule.userId = user.userId" +
                "WHERE user.username = ? OR schedule.modifyDate = ?";

        List<ScheduleResponseDto> result = jdbcTemplate.query(sql, scheduleRowMapper(), userName, modifyDate);
        return result.stream().findAny();
    }

    // // 사용자 이름, 수정일을 모두 입력 시 조회
    @Override
    public Optional<ScheduleResponseDto> findScheduleByuserNameAndmodifyDate(String userName, Date modifyDate) {
        String sql = "SELECT schedule.scheduleId, schedule.title, schedule.contents, schedule.createDate, schedule.modifyDate " +
                "FROM schedule" +
                "JOIN user ON schedule.userId = user.userId " +
                "WHERE user.username = ? AND schedule.modifyDate = ?";

        List<ScheduleResponseDto> result = jdbcTemplate.query(sql, scheduleRowMapper(), userName, modifyDate);
        return result.stream().findAny();
    }


    @Override
    public int updateSchedule(Long scheduleId, String userName, String contents) {
        String sql = "UPDATE user" +
                "JOIN schedule ON user.userId = schedule.userId" +
                "SET user.username = ?, schedule.contents = ?" +
                "WHERE schedule.scheduleId = ?";

        return jdbcTemplate.update(sql, userName,contents, scheduleId);
    }

    @Override
    public int deleteSchedule(Long scheduleId) {
        return jdbcTemplate.update("delete from schedule where scheduleId = ?", scheduleId);
    }

}