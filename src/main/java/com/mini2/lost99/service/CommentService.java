package com.mini2.lost99.service;

import com.mini2.lost99.dto.CommentRequestDto;
import com.mini2.lost99.dto.CommentResponseDto;
import com.mini2.lost99.model.Comment;
import com.mini2.lost99.model.Contents;
import com.mini2.lost99.model.User;
import com.mini2.lost99.repository.CommentRepository;
import com.mini2.lost99.repository.ContentsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ContentsRepository contentsRepository;

    public CommentService(CommentRepository commentRepository, ContentsRepository contentsRepository){
        this.commentRepository = commentRepository;
        this.contentsRepository = contentsRepository;
    }

    public List<CommentResponseDto> getAllComments(long id) {
        List<Comment> comments = commentRepository.findByidOrderByModifiedAtDesc(id);
        List<CommentResponseDto> commentResponseDtos = new ArrayList<>();

            for (Comment comment : comments) {
                CommentResponseDto commentResponseDto = new CommentResponseDto(
                        comment.getId(),
                        comment.getComment(),
                        comment.getCreatedAt(),
                        comment.getModifiedAt(),
                        comment.getUser().getUsername()
                );
                commentResponseDtos.add(commentResponseDto);
            }

            return commentResponseDtos;
    }

    public CommentResponseDto writeComment(CommentRequestDto commentRequestDto, long id, User user) {
        Contents contents = contentsRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("게시물이 존재하지 않습니다.")
        );
        Comment comment = new Comment(commentRequestDto,contents,user);
        commentRepository.save(comment);

        return new CommentResponseDto(
                comment.getId(),
                comment.getComment(),
                comment.getCreatedAt(),
                comment.getModifiedAt(),
                comment.getUser().getUsername()
        );
    }

    public void deleteComment(long commentId) {
        commentRepository.deleteById(commentId);
    }

    public void editComment(CommentRequestDto commentRequestDto, long commentId) {

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );
        comment.setComment(commentRequestDto.getComment());
        commentRepository.save(comment);
    }
}
