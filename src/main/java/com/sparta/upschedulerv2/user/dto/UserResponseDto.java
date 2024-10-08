package com.sparta.upschedulerv2.user.dto;

import java.security.Timestamp;
import java.time.LocalDateTime;

public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
    private String role;
    private LocalDateTime createdAt;  // LocalDateTime으로 변경
    private LocalDateTime updatedAt;  // LocalDateTime으로 변경

    // 모든 필드를 받는 생성자
    public UserResponseDto(Long id, String username, String email, String role, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}