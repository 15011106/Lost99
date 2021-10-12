package com.mini2.lost99.model;

import com.mini2.lost99.dto.ContentsRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Contents extends Timestamped{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String imageUrl;

    @OneToMany(mappedBy = "contents",cascade = CascadeType.REMOVE)
    List<Comment> comment = new ArrayList<>();


    public Contents(ContentsRequestDto contentsRequestDto){
        this.username = contentsRequestDto.getUsername();
        this.contents = contentsRequestDto.getContents();
        this.title = contentsRequestDto.getTitle();
        this.imageUrl = contentsRequestDto.getImageUrl();
    }
}
