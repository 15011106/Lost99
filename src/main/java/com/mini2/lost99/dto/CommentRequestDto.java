package com.mini2.lost99.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentRequestDto {

    private String comment;

    public CommentRequestDto(String comment) {
        this.comment = comment;
    }
}