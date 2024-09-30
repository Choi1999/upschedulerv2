package com.sparta.upschedulerv2.user;

import com.sparta.upschedulerv2.config.PasswordEncoder;
import com.sparta.upschedulerv2.user.dto.UserRequestDto;
import com.sparta.upschedulerv2.user.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;  // PasswordEncoder 추가

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;  // PasswordEncoder 주입
    }

    // 유저 생성 (회원가입)
    @Transactional
    public UserResponseDto register(UserRequestDto userRequestDto) {
        if (userRepository.findByEmail(userRequestDto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        String role = userRequestDto.getRole() != null ? userRequestDto.getRole() : "ROLE_USER";

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(userRequestDto.getPassword());

        User user = new User(
                userRequestDto.getUsername(),
                userRequestDto.getEmail(),
                encodedPassword,  // 암호화된 비밀번호 사용
                role
        );

        User savedUser = userRepository.save(user);

        return new UserResponseDto(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getRole(),
                savedUser.getCreatedAt(),
                savedUser.getUpdatedAt()
        );
    }

    // 유저 인증 (로그인)
    public UserResponseDto authenticate(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        // 암호화된 비밀번호와 입력된 비밀번호 비교
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    // 유저 정보 조회
    public UserResponseDto getUser(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    // 비밀번호 변경
    @Transactional
    public void changePassword(String userEmail, String newPassword) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 새 비밀번호 암호화 및 저장
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.changePassword(encodedPassword);
        userRepository.save(user);
    }

    // 유저 삭제
    @Transactional
    public void deleteUser(String userEmail) {
        if (!userRepository.existsByEmail(userEmail)) {
            throw new RuntimeException("User not found.");
        }
        userRepository.deleteByEmail(userEmail);
    }
}