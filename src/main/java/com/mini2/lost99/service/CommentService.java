package com.mini2.lost99.service;

import com.mini2.lost99.dto.CommentRequestDto;
import com.mini2.lost99.dto.CommentResponseDto;
import com.mini2.lost99.model.Comment;
import com.mini2.lost99.model.Contents;
import com.mini2.lost99.model.User;
import com.mini2.lost99.repository.CommentRepository;
import com.mini2.lost99.repository.ContentsRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    // Id에 해당하는 댓글 전체 get
    public List<CommentResponseDto> readComments(Long id) {
        List<Comment> comments = commentRepository.findByContentsId(id);
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

    // 댓글 작성
    @Transactional
    public void writeComment(CommentRequestDto commentRequestDto, Long id, User user) {
        Contents contents = contentsRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("게시물이 존재하지 않습니다.")
        );
        Comment comment = new Comment(commentRequestDto,contents, user);
        commentRepository.save(comment);

    }

    public void deleteComment(Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new IllegalArgumentException("게시물이 존재하지 않습니다.")
        );
        if(!comment.getUser().getId().equals(user.getId())){
            throw new IllegalArgumentException("로그인 한 사용자와, 댓글 작성자가 다릅니다.");
        }
        commentRepository.delete(comment);
    }

    public CommentResponseDto editComment(CommentRequestDto commentRequestDto, Long commentId, User user) {

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );
        if(!comment.getUser().getId().equals(user.getId())){
            throw new IllegalArgumentException("로그인 한 사용자와, 댓글 작성자가 다릅니다.");
        }
        comment.setComment(commentRequestDto.getComment());
        commentRepository.save(comment);

        return new CommentResponseDto(
                comment.getId(),
                comment.getComment(),
                comment.getCreatedAt(),
                comment.getModifiedAt(),
                comment.getUser().getUsername()
        );
    }
}
