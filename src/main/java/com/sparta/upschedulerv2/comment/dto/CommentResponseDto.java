package com.sparta.upschedulerv2.comment.dto;

import java.security.Timestamp;

public class CommentResponseDto {
    private Long id;
    private String content;
    private String username;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public CommentResponseDto(Long id, String content, String username, java.sql.Timestamp createdAt, java.sql.Timestamp updatedAt) {
    }

    // Getter 및 Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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