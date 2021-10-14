package com.mini2.lost99.controller;

import com.mini2.lost99.dto.CommentRequestDto;
import com.mini2.lost99.model.Comment;
import com.mini2.lost99.repository.CommentRepository;
import com.mini2.lost99.security.UserDetailsImpl;
import com.mini2.lost99.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final CommentRepository commentRepository;

    //댓글목록조회
    @GetMapping("/api/contents/{contentsId}/comment")
    public List<Comment> getComment(@PathVariable Long contentsId){
        return commentService.commentRead(contentsId);
    }

    //댓글작성
    @ResponseBody
    @PostMapping("/api/contents/{contentsId}/comment")
    public Comment createComment(@PathVariable Long contentsId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        Comment comment = commentService.commentSave(contentsId, commentRequestDto, userDetails.getUser());
        return comment;
    }

    //댓글 수정
    @PutMapping("/api/contents/{contentsId}/comment/{id}")
    public void updateComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentService.commentUpdate(id, commentRequestDto);
    }

    //댓글 삭제
    @DeleteMapping("/api/contents/{contentsId}/comment/{id}")
    public void deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentService.commentDelete(id);
    }
}