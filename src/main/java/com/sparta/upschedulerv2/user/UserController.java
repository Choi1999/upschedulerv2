package com.sparta.upschedulerv2.user;

import com.sparta.upschedulerv2.user.dto.UserRequestDto;
import com.sparta.upschedulerv2.user.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 유저 회원가입
    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto createdUser = userService.createUser(userRequestDto);
        return ResponseEntity.status(201).body(createdUser);
    }

    // 유저 조회
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long userId) {
        UserResponseDto user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    // 유저 삭제
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    // 유저 비밀번호 변경
    @PutMapping("/{userId}/password")
    public ResponseEntity<Void> changePassword(@PathVariable Long userId, @RequestBody String newPassword) {
        userService.changePassword(userId, newPassword);
        return ResponseEntity.ok().build();
    }
}