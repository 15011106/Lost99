package com.mini2.lost99.dto;

import com.mini2.lost99.model.Timestamped;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ContentsResponseDto{

    private Long id;
    private String phonenumber;
    private String username;
    private String title;
    private String contents;
    private String location;
    private String imageUrl;

    public ContentsResponseDto(Long id,
                               String title,
                               String username,
                               String phonenumber,
                               String contents,
                               String imageUrl,
                               String location,
                               LocalDateTime createdAt,
                               LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.username = username;
        this.phonenumber = phonenumber;
        this.contents = contents;
        this.imageUrl = imageUrl;
        this.location = location;

    }
}
