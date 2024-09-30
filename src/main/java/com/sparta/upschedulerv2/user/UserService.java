package com.sparta.upschedulerv2.user;

import com.sparta.upschedulerv2.user.dto.UserRequestDto;
import com.sparta.upschedulerv2.user.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 유저 생성 (회원가입) - 기본 권한을 ROLE_USER로 설정
    @Transactional
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        String role = userRequestDto.getRole() != null ? userRequestDto.getRole() : "ROLE_USER";  // role 값이 없으면 기본값 "ROLE_USER"로 설정

        User user = new User(
                userRequestDto.getUsername(),
                userRequestDto.getEmail(),
                userRequestDto.getPassword(),
                role  // role이 지정되지 않았으면 기본값 "ROLE_USER"
        );

        User savedUser = userRepository.save(user);

        // 타임스탬프 필드가 엔티티에서 상속되어 제대로 작동하는지 확인
        return new UserResponseDto(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getRole(),
                savedUser.getCreatedAt(),  // TimeStamp에서 상속된 createdAt (LocalDateTime으로 반환됨)
                savedUser.getUpdatedAt()   // TimeStamp에서 상속된 updatedAt (LocalDateTime으로 반환됨)
        );
    }

    // 유저 조회
    public UserResponseDto getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));

        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt(),  // LocalDateTime 반환
                user.getUpdatedAt()   // LocalDateTime 반환
        );
    }

    // 유저 삭제
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found.");
        }
        userRepository.deleteById(userId);
    }

    // 비밀번호 변경
    @Transactional
    public void changePassword(Long userId, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));
        user.changePassword(newPassword);
        userRepository.save(user);
    }
}
