package com.mini2.lost99.controller;

import com.mini2.lost99.dto.ContentsRequestDto;
import com.mini2.lost99.dto.ContentsResponseDto;
import com.mini2.lost99.repository.ContentsRepository;
import com.mini2.lost99.security.UserDetailsImpl;
import com.mini2.lost99.service.ContentsService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
public class ContentsController {
    private final ContentsService contentsService;
    private final ContentsRepository contentsRepository;


    public ContentsController( ContentsService contentsService, ContentsRepository contentsRepository){
            this.contentsService = contentsService;
            this.contentsRepository = contentsRepository;
    }

    // 게시글 전체 출력
    @GetMapping("/api/contents")
    public List<ContentsResponseDto> readContents() {
        return contentsService.getAllContents();
    }

    // 게시글 작성
    @PostMapping("/api/contents")
    public void writeContents(@RequestBody ContentsRequestDto contentsRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        contentsRequestDto.setUsername(userDetails.getUsername());
        contentsService.contentsSave(contentsRequestDto);
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
