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
    @GetMapping("/api/contents/{contentsId}/comments")
    public List<CommentResponseDto> readComments(@PathVariable Long contentsId) {

        return commentService.readComments(contentsId);
    }

    //댓글 작성

    @PostMapping("/api/contents/{contentsId}/comments")
    public void writeComment(@PathVariable Long contentsId, @RequestBody CommentRequestDto commentRequestDto) {
//        // 로그인 되어 있는 ID
//        if (userDetails == null) {
//            throw new IllegalArgumentException("로그인을 해야 댓글을 작성할 수 있습니다.");
//        }
        commentService.writeComment(commentRequestDto, contentsId);

    }

    //댓글 삭제
    @DeleteMapping("/api/contents/comments/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {

//        if(userDetails==null){
//            throw new IllegalArgumentException("로그인을 해야 댓글을 삭제할 수 있습니다.");
//        }
        commentService.deleteComment(commentId);
    }


    //수정 하기
    @PutMapping("/api/contents/comments/{commentId}")
    public void editComment(@RequestBody CommentRequestDto commentRequestDto,
                            @PathVariable Long commentId) {
//        if(userDetails==null){
//            throw new IllegalArgumentException("로그인을 해야 댓글을 수정할 수 있습니다.");
//        }
        commentService.editComment(commentRequestDto, commentId);

    }
}