package com.sparta.upschedulerv2.schedule;


import com.sparta.upschedulerv2.schedule.dto.ScheduleRequestDto;
import com.sparta.upschedulerv2.schedule.dto.ScheduleResponseDto;
import com.sparta.upschedulerv2.user.User;
import com.sparta.upschedulerv2.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    // 일정 생성
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));

        Schedule schedule = new Schedule(
                requestDto.getTitle(),
                requestDto.getDescription(),
                user
        );

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getDescription(),
                savedSchedule.getUser().getUsername(),
                savedSchedule.getComments().size(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getUpdatedAt()
        );
    }

    // 일정 조회
    public ScheduleResponseDto getSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule not found."));
        return new ScheduleResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getDescription(),
                schedule.getUser().getUsername(),
                schedule.getComments().size(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }

    // 일정 수정
    public ScheduleResponseDto updateSchedule(Long scheduleId, ScheduleRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule not found."));

        schedule.updateSchedule(requestDto.getTitle(), requestDto.getDescription());
        Schedule updatedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(
                updatedSchedule.getId(),
                updatedSchedule.getTitle(),
                updatedSchedule.getDescription(),
                updatedSchedule.getUser().getUsername(),
                updatedSchedule.getComments().size(),
                updatedSchedule.getCreatedAt(),
                updatedSchedule.getUpdatedAt()
        );
    }

    // 일정 삭제
    public void deleteSchedule(Long scheduleId) {
        if (!scheduleRepository.existsById(scheduleId)) {
            throw new RuntimeException("Schedule not found.");
        }
        scheduleRepository.deleteById(scheduleId);
    }
}
