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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Manager> managers = new ArrayList<>();

    public Schedule(String title, String description, User user) {
        this.title = title;
        this.description = description;
        this.user = user;
    }

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

    public void updateSchedule(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void addManager(Manager manager) {
        this.managers.add(manager);
    }
}