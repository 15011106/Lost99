package com.mini2.lost99.dto;

import com.mini2.lost99.model.Contents;
import com.mini2.lost99.model.User;

import java.time.LocalDateTime;

public class CommentResponseDto {

    private final Long id;
    private final String comment;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final String commentUsername;

    public CommentResponseDto(Long id, String comment, LocalDateTime createdAt, LocalDateTime modifiedAt, String commentUsername) {
        this.id = id;
        this.comment = comment;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.commentUsername = commentUsername;
    }

}
