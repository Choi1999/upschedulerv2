package com.sparta.upschedulerv2.schedule;


import com.sparta.upschedulerv2.manager.ManagerRepository;
import com.sparta.upschedulerv2.schedule.dto.ScheduleRequestDto;
import com.sparta.upschedulerv2.schedule.dto.ScheduleResponseDto;
import com.sparta.upschedulerv2.user.User;
import com.sparta.upschedulerv2.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;


    public ScheduleService(ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    // 일정 생성
    @Transactional
    public ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleRequestDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Schedule schedule = new Schedule(scheduleRequestDto.getTitle(), scheduleRequestDto.getDescription(), user);
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getDescription(),
                savedSchedule.getUser().getUsername(),
                savedSchedule.getComments().size(),
                savedSchedule.getCreatedAt(),  // LocalDateTime
                savedSchedule.getUpdatedAt()   // LocalDateTime
        );
    }

    // 일정 조회
    public ScheduleResponseDto getSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        return new ScheduleResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getDescription(),
                schedule.getUser().getUsername(),
                schedule.getComments().size(),
                schedule.getCreatedAt(),  // LocalDateTime
                schedule.getUpdatedAt()   // LocalDateTime
        );
    }


    // 일정 페이징 조회 (수정일 기준 내림차순)
    @Transactional(readOnly = true)
    public Page<ScheduleResponseDto> getSchedules(int page, int size) {
        Pageable pageable = (Pageable) PageRequest.of(page, size, Sort.by("updatedAt").descending());

        return scheduleRepository.findAll((org.springframework.data.domain.Pageable) pageable).map(schedule ->
                new ScheduleResponseDto(
                        schedule.getId(),
                        schedule.getTitle(),
                        schedule.getDescription(),
                        schedule.getUser().getUsername(),
                        schedule.getComments().size(),
                        schedule.getCreatedAt(),
                        schedule.getUpdatedAt()
                )
        );
    }

    // 일정 삭제
    @Transactional
    public void deleteSchedule(Long scheduleId) {
        if (!scheduleRepository.existsById(scheduleId)) {
            throw new RuntimeException("Schedule not found.");
        }
        scheduleRepository.deleteById(scheduleId);
    }

    // 일정 수정
    @Transactional
    public ScheduleResponseDto updateSchedule(Long scheduleId, ScheduleRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        schedule.updateSchedule(requestDto.getTitle(), requestDto.getDescription());
        Schedule updatedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(
                updatedSchedule.getId(),
                updatedSchedule.getTitle(),
                updatedSchedule.getDescription(),
                updatedSchedule.getUser().getUsername(),
                updatedSchedule.getComments().size(),
                updatedSchedule.getCreatedAt(),  // LocalDateTime
                updatedSchedule.getUpdatedAt()   // LocalDateTime
        );
    }
}