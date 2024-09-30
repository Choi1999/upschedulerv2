package com.sparta.upschedulerv2.schedule;

import com.sparta.upschedulerv2.manager.ManagerService;
import com.sparta.upschedulerv2.manager.dto.ManagerRequestDto;
import com.sparta.upschedulerv2.manager.dto.ManagerResponseDto;
import com.sparta.upschedulerv2.schedule.dto.ScheduleRequestDto;
import com.sparta.upschedulerv2.schedule.dto.ScheduleResponseDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final ManagerService managerService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService,ManagerService managerService) {
        this.scheduleService = scheduleService;
        this.managerService = managerService;
    }

    // 일정 생성
    @PostMapping("/{userId}")
    public ResponseEntity<ScheduleResponseDto> createSchedule(@PathVariable Long userId, @RequestBody ScheduleRequestDto requestDto) {
        ScheduleResponseDto createdSchedule = scheduleService.createSchedule(requestDto, userId);
        return ResponseEntity.status(201).body(createdSchedule);
    }

    // 일정 조회
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> getSchedule(@PathVariable Long scheduleId) {
        ScheduleResponseDto schedule = scheduleService.getSchedule(scheduleId);
        return ResponseEntity.ok(schedule);
    }

    // 일정 수정
    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long scheduleId, @RequestBody ScheduleRequestDto requestDto) {
        ScheduleResponseDto updatedSchedule = scheduleService.updateSchedule(scheduleId, requestDto);
        return ResponseEntity.ok(updatedSchedule);
    }

    // 일정 삭제
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.noContent().build();
    }
    // 일정에 매니저 배정
    @PostMapping("/{scheduleId}/managers")
    public ResponseEntity<ManagerResponseDto> assignManager(@PathVariable Long scheduleId, @RequestBody ManagerRequestDto managerRequestDto) {
        ManagerResponseDto assignedManager = managerService.assignManager(scheduleId, managerRequestDto);
        return ResponseEntity.status(201).body(assignedManager);
    }
}