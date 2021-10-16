package com.mini2.lost99.controller;

import com.mini2.lost99.dto.CommentRequestDto;
import com.mini2.lost99.dto.CommentResponseDto;
import com.mini2.lost99.security.UserDetailsImpl;
import com.mini2.lost99.service.CommentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class CommentController {

    public CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 불러오기
    @GetMapping("/api/contents/{id}/comments")
    public List<CommentResponseDto> readComments(@PathVariable long id) {

        return commentService.getAllComments(id);
    }

    //댓글 작성
    @PostMapping("/api/contents/{id}/comments")
    public CommentResponseDto writeComment(@PathVariable long id, CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.writeComment(commentRequestDto, id, userDetails.getUser());

    }

    //댓글 삭제
    @DeleteMapping("/api/contents/{id}/comments/{commentId}")
    public void deleteComment(@PathVariable long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        commentService.deleteComment(commentId);
    }


    @PutMapping("/api/contents/{id}/comments/{commentId}")
    public void editComment(@RequestBody CommentRequestDto commentRequestDto ,@PathVariable long id, @PathVariable long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        commentService.editComment(commentRequestDto,commentId);

    }
}