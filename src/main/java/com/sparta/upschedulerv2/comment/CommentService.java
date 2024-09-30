package com.sparta.upschedulerv2.comment;


import com.sparta.upschedulerv2.comment.dto.CommentRequestDto;
import com.sparta.upschedulerv2.comment.dto.CommentResponseDto;
import com.sparta.upschedulerv2.schedule.Schedule;
import com.sparta.upschedulerv2.schedule.ScheduleRepository;
import com.sparta.upschedulerv2.user.User;
import com.sparta.upschedulerv2.user.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    // 댓글 생성
    @Transactional
    public CommentResponseDto createComment(Long scheduleId, Long userId, CommentRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Comment comment = new Comment(schedule, user, requestDto.getContent());
        Comment savedComment = commentRepository.save(comment);

        return new CommentResponseDto(
                savedComment.getId(),
                savedComment.getContent(),
                savedComment.getUser().getUsername(),
                savedComment.getCreatedAt(),  // LocalDateTime으로 변경
                savedComment.getUpdatedAt()   // LocalDateTime으로 변경
        );
    }

    // 댓글 단건 조회
    @Transactional(readOnly = true)
    public CommentResponseDto getComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        return new CommentResponseDto(
                comment.getId(),
                comment.getContent(),
                comment.getUser().getUsername(),
                comment.getCreatedAt(),  // LocalDateTime으로 변경
                comment.getUpdatedAt()   // LocalDateTime으로 변경
        );
    }

    // 댓글 수정
    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        comment.updateContent(requestDto.getContent());
        Comment updatedComment = commentRepository.save(comment);

        return new CommentResponseDto(
                updatedComment.getId(),
                updatedComment.getContent(),
                updatedComment.getUser().getUsername(),
                updatedComment.getCreatedAt(),  // LocalDateTime으로 변경
                updatedComment.getUpdatedAt()   // LocalDateTime으로 변경
        );
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(Long commentId) {
        if (!commentRepository.existsById(commentId)) {
            throw new RuntimeException("Comment not found");
        }
        commentRepository.deleteById(commentId);
    }
    // 일정에 대한 모든 댓글 조회
    @Transactional(readOnly = true)
    public List<CommentResponseDto> getCommentsForSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        List<Comment> comments = commentRepository.findBySchedule(schedule);

        return comments.stream()
                .map(comment -> new CommentResponseDto(
                        comment.getId(),
                        comment.getContent(),
                        comment.getUser().getUsername(),
                        comment.getCreatedAt(),  // LocalDateTime
                        comment.getUpdatedAt()   // LocalDateTime
                ))
                .collect(Collectors.toList());
    }
}