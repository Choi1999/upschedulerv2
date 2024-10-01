package com.sparta.upschedulerv2.config;

public class UserInfoDto {
    private Long id;
    private String username;
    private String email;
    private String role;

    // 생성자
    public UserInfoDto(Long id, String username, String email, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    // Getters
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
}