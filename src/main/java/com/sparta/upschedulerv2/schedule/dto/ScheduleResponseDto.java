package com.sparta.upschedulerv2.schedule.dto;

import java.security.Timestamp;

public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String description;
    private String username;
    private int commentCount;  // 댓글 개수
    private Timestamp createdAt;  // 생성일시
    private Timestamp updatedAt;  // 수정일시

    // 기본 생성자
    public ScheduleResponseDto(Long id, String title, String description, String username, int size, java.sql.Timestamp createdAt, java.sql.Timestamp updatedAt) {
    }

    // 모든 필드를 받는 생성자
    public ScheduleResponseDto(Long id, String title, String description, String username, int commentCount, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.username = username;
        this.commentCount = commentCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getter 및 Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}