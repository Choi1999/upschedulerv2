package com.sparta.upschedulerv2.manager.dto;

public class ManagerResponseDto {
    private Long id;
    private String username;  // 담당자 이름
    private String email;     // 담당자 이메일

    public ManagerResponseDto(Long id, String username, String email) {
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
}