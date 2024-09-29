package com.sparta.upschedulerv2.user;

import com.sparta.upschedulerv2.config.TimeStamp;
import com.sparta.upschedulerv2.schedule.Schedule;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
// 기본 생성자 제공, 외부에서 접근 못하게 보호
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;  // 유저명

    @Column(nullable = false, unique = true)
    private String email;  // 이메일

    @Column(nullable = false)
    private String password;  // 비밀번호 (암호화된 상태로 저장)

    @Column(nullable = false)
    private String role;  // 유저의 권한 (ROLE_USER 또는 ROLE_MANAGER)

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Schedule> schedules = new ArrayList<>();  // 일정을 저장하는 리스트

    // 사용자 정의 생성자 (필수 필드 초기화)
    public User(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getter 메서드 (필요한 필드만)
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    // 비밀번호 변경을 위한 메서드
    public void changePassword(String newPassword) {
        this.password = newPassword;  // 암호화된 비밀번호로 변경
    }

    // 일정 추가 메서드
    public void addSchedule(Schedule schedule) {
        this.schedules.add(schedule);
    }
}