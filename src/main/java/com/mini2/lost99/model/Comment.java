package com.mini2.lost99.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mini2.lost99.dto.CommentRequestDto;
import com.mini2.lost99.dto.ContentsRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.security.cert.TrustAnchor;
import java.time.LocalDateTime;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Comment extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String comment;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;

    @ManyToOne
    @JoinColumn(nullable = true)
    private User user;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(nullable = false)
    private Contents contents;


    public Comment(CommentRequestDto commentRequestDto, Contents contents, User user){
        this.comment = commentRequestDto.getComment();
        this.contents = contents;
        this.user = user;
    }

    public Comment(CommentRequestDto commentRequestDto, Contents contents) {
        this.comment = commentRequestDto.getComment();
        this.contents = contents;
        this.user = null;
    }
}


