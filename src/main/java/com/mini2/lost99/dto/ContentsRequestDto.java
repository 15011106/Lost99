package com.mini2.lost99.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ContentsRequestDto {
    public ContentsRequestDto( String username, String title,String contents, String imageUrl, String location, String phonenumber) {
        this.username = username;
        this.title = title;
        this.contents = contents;
        this.imageUrl = imageUrl;
        this.location = location;
        this.phonenumber = phonenumber;
    }

    private String username;
    private String imageUrl;
    private String title;
    private String contents;
    private String phonenumber;
    private String location;
}
