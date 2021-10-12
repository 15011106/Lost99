package com.mini2.lost99.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ContentsResponseDto {
    public ContentsResponseDto(Long id, String contentTitle, String username, String title, LocalDateTime createdAt, LocalDateTime modifiedAt, String contents) {
        this.username = username;
        this.title = title;
        this.contents = contents;
    }

    private String username;
    private String title;
    private String contents;
}
