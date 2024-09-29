package com.sparta.upschedulerv2.comment;

import com.sparta.upschedulerv2.config.TimeStamp;
import com.sparta.upschedulerv2.schedule.Schedule;
import com.sparta.upschedulerv2.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 기본 생성자 보호
public class Comment extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;  // 댓글이 속한 일정

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // 댓글 작성자

    @Column(nullable = false)
    private String content;  // 댓글 내용

    // 생성자
    public Comment(Schedule schedule, User user, String content) {
        this.schedule = schedule;
        this.user = user;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    // 댓글 내용 수정 메서드
    public void updateContent(String content) {
        this.content = content;
    }
}