package com.mini2.lost99.controller;

import com.mini2.lost99.dto.CommentRequestDto;
import com.mini2.lost99.dto.CommentResponseDto;
import com.mini2.lost99.security.UserDetailsImpl;
import com.mini2.lost99.service.CommentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class CommentController {

    public CommentService commentService;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 불러오기
    @ResponseBody
    @GetMapping("/api/contents/{id}/comments")
    public List<CommentResponseDto> readComments(@PathVariable long id) {

        return commentService.readComments(id);
    }

    //댓글 작성
    @ResponseBody
    @PostMapping("/api/contents/{id}/comments")
    public void writeComment(@PathVariable long id, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        commentService.writeComment(commentRequestDto, id, userDetails.getUser());

    }

    //댓글 삭제
    @ResponseBody
    @DeleteMapping("/api/contents/comments/{commentId}")
    public void deleteComment(@PathVariable long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.deleteComment(commentId, userDetails.getUser());
    }


    //수정 하기
    @ResponseBody
    @PutMapping("/api/contents/comments/{commentId}")
    public void editComment(@RequestBody CommentRequestDto commentRequestDto ,@PathVariable long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.editComment(commentRequestDto,commentId, userDetails.getUser());

    }
}