package com.mini2.lost99.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ContentsRequestDto {
    public ContentsRequestDto( String username, String title,String contents, String imageUrl) {
        this.username = username;
        this.title = title;
        this.contents = contents;
        this.imageUrl = imageUrl;
    }

    private String username;
    private String imageUrl;
    private String title;
    private String contents;
}
