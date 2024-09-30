package com.sparta.upschedulerv2.manager;


import com.sparta.upschedulerv2.manager.dto.ManagerRequestDto;
import com.sparta.upschedulerv2.manager.dto.ManagerResponseDto;
import com.sparta.upschedulerv2.schedule.Schedule;
import com.sparta.upschedulerv2.schedule.ScheduleRepository;
import com.sparta.upschedulerv2.user.User;
import com.sparta.upschedulerv2.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Autowired
    public ManagerService(ManagerRepository managerRepository, ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.managerRepository = managerRepository;
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    // 매니저 지정
    public ManagerResponseDto assignManager(Long scheduleId, ManagerRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule not found."));
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found."));

        Manager manager = new Manager(schedule, user);
        Manager savedManager = managerRepository.save(manager);

        return new ManagerResponseDto(
                savedManager.getId(),
                savedManager.getUser().getUsername(),
                savedManager.getUser().getEmail()
        );
    }
}