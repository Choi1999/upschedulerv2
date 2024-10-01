package com.sparta.upschedulerv2.user;

import com.sparta.upschedulerv2.config.CurrentUser;
import com.sparta.upschedulerv2.config.JwtUtil;
import com.sparta.upschedulerv2.user.dto.UserRequestDto;
import com.sparta.upschedulerv2.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    // 회원가입 및 JWT 발급
    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto user = userService.register(userRequestDto);
        String token = jwtUtil.createToken(user.getEmail(), user.getRole());
        return ResponseEntity.ok()
                .header("Authorization", "Bearer " + token)  // 헤더에 JWT 토큰 추가
                .body(user);  // User 정보 반환
    }

    // 로그인 및 JWT 발급
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserRequestDto requestDto) {
        UserResponseDto user = userService.authenticate(requestDto.getEmail(), requestDto.getPassword());
        String token = jwtUtil.createToken(user.getEmail(), user.getRole());
        return ResponseEntity.ok("Bearer " + token);
    }

    // 유저 정보 조회
    @GetMapping("/me")
    public ResponseEntity<String> getCurrentUser(@CurrentUser String userEmail) {
        return ResponseEntity.ok("Current user email: " + userEmail);
    }

    // 유저 비밀번호 변경
    @PutMapping("/password")
    public ResponseEntity<Void> changePassword(@RequestBody PasswordChangeRequestDto requestDto, HttpServletRequest request) {
        String userEmail = (String) request.getAttribute("userEmail");
        userService.changePassword(userEmail, requestDto.getNewPassword());
        return ResponseEntity.noContent().build();
    }

    // 유저 삭제
    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteUser(HttpServletRequest request) {
        String userEmail = (String) request.getAttribute("userEmail");
        userService.deleteUser(userEmail);
        return ResponseEntity.noContent().build();
    }
}