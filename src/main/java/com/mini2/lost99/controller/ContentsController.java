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
    @GetMapping("/api/contents")
    public List<ContentsResponseDto> readContents() {
        return contentsService.getAllContents();
    }

    // 게시글 작성
    @ResponseBody
    @PostMapping("/api/contents")
    public Contents writeContents(@RequestBody ContentsRequestDto contentsRequestDto)
    {
        Contents contents = contentsService.contentsSave(contentsRequestDto);
        return contents;
    }

    // 게시글 수정
    @PutMapping("/api/contents/{id}")
    public void editContents(@PathVariable Long id, @RequestBody ContentsRequestDto contentsRequestDto ,@AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        contentsService.contentsUpdate(id,contentsRequestDto);
    }

    // 게시글 삭제
    @DeleteMapping("/api/contents/{id}")
    public void deleteContents(@PathVariable Long id,@AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        contentsService.contentsDelete(id);
    }

}
