package com.sparta.upschedulerv2.comment;

import com.sparta.upschedulerv2.comment.dto.CommentRequestDto;
import com.sparta.upschedulerv2.comment.dto.CommentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules/{scheduleId}/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 생성
    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(
            @PathVariable Long scheduleId,
            @RequestParam Long userId,
            @RequestBody CommentRequestDto commentRequestDto) {
        CommentResponseDto createdComment = commentService.createComment(scheduleId, userId, commentRequestDto);
        return ResponseEntity.status(201).body(createdComment);  // 201 Created
    }

    // 댓글 단건 조회
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> getComment(@PathVariable Long commentId) {
        CommentResponseDto comment = commentService.getComment(commentId);
        return ResponseEntity.ok(comment);  // 200 OK
    }

    // 댓글 수정
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentRequestDto commentRequestDto) {
        CommentResponseDto updatedComment = commentService.updateComment(commentId, commentRequestDto);
        return ResponseEntity.ok(updatedComment);  // 200 OK
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();  // 204 No Content
    }

    // 일정에 대한 모든 댓글 조회
    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getCommentsForSchedule(@PathVariable Long scheduleId) {
        List<CommentResponseDto> comments = commentService.getCommentsForSchedule(scheduleId);
        return ResponseEntity.ok(comments);  // 200 OK
    }
}