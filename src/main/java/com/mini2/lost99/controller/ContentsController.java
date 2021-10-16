package com.mini2.lost99.controller;

import com.mini2.lost99.dto.ContentsRequestDto;
import com.mini2.lost99.dto.ContentsResponseDto;
import com.mini2.lost99.model.Contents;
import com.mini2.lost99.security.UserDetailsImpl;
import com.mini2.lost99.service.ContentsService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ContentsController {
    private final ContentsService contentsService;


    public ContentsController( ContentsService contentsService){
            this.contentsService = contentsService;
    }

    // 게시글 전체 출력
    @ResponseBody
    @GetMapping("/api/contents")
    public List<ContentsResponseDto> readContents() {

        return contentsService.getAllContents();
    }

    // 게시글 작성
    @ResponseBody
    @PostMapping("/api/contents")
    public Contents writeContents(@RequestBody ContentsRequestDto contentsRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        System.out.println(userDetails);
        if (userDetails == null) {
            throw new IllegalArgumentException("로그인을 해야 게시글을 작성하실 수 있습니다.");
        }

        Contents contents = contentsService.contentsSave(contentsRequestDto);
        return contents;
    }

    // 상세 게시글 출력

    @ResponseBody
    @GetMapping("/api/contents/{id}")
    public ContentsResponseDto readContent(@PathVariable Long id) {
        return contentsService.readContent(id);

    }

    // 게시글 수정

    @PutMapping("/api/contents/{id}")
    public void editContents(@PathVariable Long id, @RequestBody ContentsRequestDto contentsRequestDto ,@AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        if (userDetails == null) {
            throw new IllegalArgumentException("게시글을 수정하실 권한이 없습니다.");
        }
        contentsService.contentsUpdate(id,contentsRequestDto);
    }

    // 게시글 삭제

    @DeleteMapping("/api/contents/{id}")
    public void deleteContents(@PathVariable Long id,@AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        if (userDetails == null) {
            throw new IllegalArgumentException("게시글을 삭제하실 권한이 없습니다.");
        }
        contentsService.contentsDelete(id);
    }

}
