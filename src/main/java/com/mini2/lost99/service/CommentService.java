package com.mini2.lost99.service;

import com.mini2.lost99.dto.CommentRequestDto;
import com.mini2.lost99.dto.CommentResponseDto;
import com.mini2.lost99.model.Comment;
import com.mini2.lost99.model.Contents;
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
// Id에 해당하는 코멘트 전체 get
    public List<CommentResponseDto> readComments(long id) {
        List<Comment> comments = commentRepository.findByContentsIdOrderByModifiedAtDesc(id);
        List<CommentResponseDto> commentResponseDtos = new ArrayList<>();

            for (Comment comment : comments) {
                CommentResponseDto commentResponseDto = new CommentResponseDto(
                        comment.getId(),
                        comment.getContents(),
                        comment.getComment(),
                        comment.getCreatedAt(),
                        comment.getModifiedAt()
                );
                commentResponseDtos.add(commentResponseDto);
            }

            return commentResponseDtos;
    }

    // 코멘트 작성
    @Transactional
    public void writeComment(CommentRequestDto commentRequestDto, long id) {
        Contents contents = contentsRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("게시물이 존재하지 않습니다.")
        );
        Comment comment = new Comment(commentRequestDto,contents);
        commentRepository.save(comment);
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
