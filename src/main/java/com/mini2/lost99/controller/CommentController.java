package com.mini2.lost99.controller;

import com.mini2.lost99.dto.CommentRequestDto;
import com.mini2.lost99.dto.CommentResponseDto;
import com.mini2.lost99.security.UserDetailsImpl;
import com.mini2.lost99.service.CommentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CommentController {

    public CommentService commentService;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 불러오기
    @GetMapping("/api/contents/{id}/comments")
    public List<CommentResponseDto> readComments(@PathVariable Long id) {

        return commentService.readComments(id);
    }

    //댓글 작성

    @PostMapping("/api/contents/{id}/comments")
    public CommentResponseDto writeComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto,
                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // 로그인 되어 있는 ID
        if (userDetails == null) {
            throw new IllegalArgumentException("로그인을 해야 댓글을 작성할 수 있습니다.");
        }
        return commentService.writeComment(commentRequestDto, id, userDetails.getUser());

    }

    //댓글 삭제
    @DeleteMapping("/api/contents/comments/{commentId}")
    public void deleteComment(@PathVariable Long commentId,  @AuthenticationPrincipal UserDetailsImpl userDetails) {

        if(userDetails==null){
            throw new IllegalArgumentException("로그인을 해야 댓글을 삭제할 수 있습니다.");
        }
        commentService.deleteComment(commentId, userDetails.getUser());
    }


    //수정 하기
    @PutMapping("/api/contents/comments/{commentId}")
    public CommentResponseDto editComment(@RequestBody CommentRequestDto commentRequestDto,
                            @PathVariable Long commentId,
                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(userDetails==null){
            throw new IllegalArgumentException("로그인을 해야 댓글을 수정할 수 있습니다.");
        }
        return commentService.editComment(commentRequestDto, commentId, userDetails.getUser());

    }
}