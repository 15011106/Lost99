package com.mini2.lost99.dto;

import com.mini2.lost99.model.Contents;
import com.mini2.lost99.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponseDto {

    private Long id;
    private String comment;
    private Contents contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String username;

    public CommentResponseDto(Long id, String comment, LocalDateTime createdAt, LocalDateTime modifiedAt, String username) {

    }


    public CommentResponseDto(Long id, Contents contents, String comment, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.contents=contents;
        this.comment = comment;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}