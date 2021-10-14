package com.mini2.lost99.service;

import com.mini2.lost99.dto.CommentRequestDto;
import com.mini2.lost99.model.Comment;
import com.mini2.lost99.model.Contents;
import com.mini2.lost99.model.User;
import com.mini2.lost99.repository.CommentRepository;
import com.mini2.lost99.repository.ContentsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ContentsRepository contentsRepository;

    public CommentService(CommentRepository commentRepository, ContentsRepository contentsRepository){
        this.commentRepository = commentRepository;
        this.contentsRepository = contentsRepository;
    }

    public List<Comment> commentRead(Long contentsId) {
        return commentRepository.findAllByContentsIdOrderByCreatedAtDesc(contentsId);
    }

    public Comment commentSave(Long contentsId, CommentRequestDto commentRequestDto, User user) {
        Contents contents = contentsRepository.findById(contentsId).orElseThrow(
                () -> new IllegalArgumentException("requested articleId가 DB에 없습니다.")
        );
        Comment comment = new Comment(commentRequestDto, user, contents);
        commentRepository.save(comment);
        return comment;
    }

    public void commentUpdate(Long id, CommentRequestDto commentRequestDto) {
        Comment commentUpdate = commentRepository.getById(id);
        commentUpdate.setComment(commentRequestDto.getComment());
        commentRepository.save(commentUpdate);
    }

    public void commentDelete(Long id) {
        commentRepository.deleteById(id);
    }
}