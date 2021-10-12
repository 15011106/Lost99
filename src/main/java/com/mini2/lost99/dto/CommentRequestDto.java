package com.mini2.lost99.dto;

import com.mini2.lost99.model.Contents;
import com.mini2.lost99.model.User;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    private User user;
    private String comment;
    private Contents contents;
}