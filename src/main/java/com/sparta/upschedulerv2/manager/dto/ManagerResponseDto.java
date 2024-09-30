package com.sparta.upschedulerv2.manager.dto;

public class ManagerResponseDto {
    private Long id;
    private String username;
    private String email;

    public ManagerResponseDto(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}