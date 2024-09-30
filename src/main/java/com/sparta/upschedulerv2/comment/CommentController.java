package com.sparta.upschedulerv2.comment;

import com.sparta.upschedulerv2.comment.dto.CommentRequestDto;
import com.sparta.upschedulerv2.comment.dto.CommentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedules/{scheduleId}/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 생성
    @PostMapping("/{userId}")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long scheduleId, @PathVariable Long userId, @RequestBody CommentRequestDto requestDto) {
        CommentResponseDto createdComment = commentService.createComment(scheduleId, requestDto, userId);
        return ResponseEntity.status(201).body(createdComment);
    }

    // 댓글 조회
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> getComment(@PathVariable Long commentId) {
        CommentResponseDto comment = commentService.getComment(commentId);
        return ResponseEntity.ok(comment);
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}