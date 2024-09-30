package com.sparta.upschedulerv2.user.dto;

public class UserRequestDto {
    private String username;
    private String email;
    private String password;
    private String role;

    // Getter 및 Setter
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getRole() {
        return role;  // 추가된 메서드: 유저의 권한을 반환
    }

}