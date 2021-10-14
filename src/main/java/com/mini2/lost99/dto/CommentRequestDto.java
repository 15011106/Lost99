package com.mini2.lost99.dto;

import com.mini2.lost99.model.Contents;
import com.mini2.lost99.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {
    private final String comment;
    private final User user;
    private final Contents contents;

    public CommentRequestDto(String comment, User user, Contents contents){
        this.comment = comment;
        this.user = user;
        this.contents = contents;
    }
}