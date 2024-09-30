package com.sparta.upschedulerv2.user.dto;

import java.security.Timestamp;

public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
    private String role;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // 기본 생성자 (필수)
    public UserResponseDto(Long id, String username, String email, String role, java.sql.Timestamp createdAt, java.sql.Timestamp updatedAt) {
    }

    // 모든 필드를 받는 생성자
    public UserResponseDto(Long id, String username, String email, String role, Timestamp createdAt, Timestamp updatedAt) {
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