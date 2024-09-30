package com.sparta.upschedulerv2.comment;


import com.sparta.upschedulerv2.comment.dto.CommentRequestDto;
import com.sparta.upschedulerv2.comment.dto.CommentResponseDto;
import com.sparta.upschedulerv2.schedule.Schedule;
import com.sparta.upschedulerv2.schedule.ScheduleRepository;
import com.sparta.upschedulerv2.user.User;
import com.sparta.upschedulerv2.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public CommentResponseDto createComment(Long scheduleId, CommentRequestDto requestDto, Long userId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule not found."));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));

        Comment comment = new Comment(schedule, user, requestDto.getContent());
        Comment savedComment = commentRepository.save(comment);

        return new CommentResponseDto(
                savedComment.getId(),
                savedComment.getContent(),
                savedComment.getUser().getUsername(),
                savedComment.getCreatedAt(),
                savedComment.getUpdatedAt()
        );
    }

    // 댓글 조회
    public CommentResponseDto getComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found."));
        return new CommentResponseDto(
                comment.getId(),
                comment.getContent(),
                comment.getUser().getUsername(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }

    // 댓글 삭제
    public void deleteComment(Long commentId) {
        if (!commentRepository.existsById(commentId)) {
            throw new RuntimeException("Comment not found.");
        }
        commentRepository.deleteById(commentId);
    }
}