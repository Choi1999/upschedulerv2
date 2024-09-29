package com.sparta.upschedulerv2.schedule;

import com.sparta.upschedulerv2.comment.Comment;
import com.sparta.upschedulerv2.config.TimeStamp;
import com.sparta.upschedulerv2.manager.Manager;
import com.sparta.upschedulerv2.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 기본 생성자 보호
public class Schedule extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;  // 일정 제목

    @Column(nullable = false)
    private String description;  // 일정 설명

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // 일정 작성자

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();  // 댓글 목록

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Manager> managers = new ArrayList<>();  // 매니저 목록

    // 생성자
    public Schedule(String title, String description, User user) {
        this.title = title;
        this.description = description;
        this.user = user;
    }

    // Getter 메서드
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public User getUser() {
        return user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<Manager> getManagers() {
        return managers;
    }

    // 일정 수정 메서드
    public void updateSchedule(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // 댓글 추가 메서드
    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    // 매니저 추가 메서드
    public void addManager(Manager manager) {
        this.managers.add(manager);
    }
}