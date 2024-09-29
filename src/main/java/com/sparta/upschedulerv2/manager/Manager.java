package com.sparta.upschedulerv2.manager;

import com.sparta.upschedulerv2.schedule.Schedule;
import com.sparta.upschedulerv2.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "managers")
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 기본 생성자 보호
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;  // 담당하는 일정

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // 담당자로 지정된 유저

    // 생성자
    public Manager(Schedule schedule, User user) {
        this.schedule = schedule;
        this.user = user;
    }

    // Getter 메서드
    public Long getId() {
        return id;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public User getUser() {
        return user;
    }
}