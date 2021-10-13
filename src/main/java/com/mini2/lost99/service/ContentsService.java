package com.mini2.lost99.service;

import com.mini2.lost99.dto.ContentsRequestDto;
import com.mini2.lost99.dto.ContentsResponseDto;
import com.mini2.lost99.model.Contents;
import com.mini2.lost99.model.User;
import com.mini2.lost99.repository.ContentsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ContentsService {
    private final ContentsRepository contentsRepository;
    public ContentsService (ContentsRepository contentsRepository){
        this.contentsRepository = contentsRepository;
    }

    public List<ContentsResponseDto> getAllContents() {
        List<Contents> contents = contentsRepository.findAllByOrderByCreatedAtDesc();
        List<ContentsResponseDto> contentsResponseDtos = new ArrayList<>();
        //계층 간 작업 시 Dto를 사용하는 습관을 갖는게 중요함.
        //Controller에서 직접 Article article을 건드리기보다 Dto를 활용하자.
        //효율성 측면에서도 좋음. Article 테이블(DB)에는 User의 정보 전부(id, username, password, email 등)가 연결되어있음.
        //내가 진짜 필요한 정보만 담아서 활용하는 것. User 전체가 아닌 User의 username만 뽑아서 쓰는 것이 효율적임.
        for(Contents content : contents){
            ContentsResponseDto contentsResponseDto = new ContentsResponseDto(
                    content.getId(),
                    content.getTitle(),
                    content.getUsername(), // <-- Dto 효율성의 좋은 예시
                    content.getContents(),
                    content.getCreatedAt(),
                    content.getModifiedAt(),
                    content.getImageUrl()
            );

            contentsResponseDtos.add(contentsResponseDto);
        }

        return contentsResponseDtos;
    }

    public Contents contentsSave(ContentsRequestDto contentsRequestDto) {
        Contents contents =new Contents(contentsRequestDto);
        contentsRepository.save(contents);

        return contents;
    }

    public void contentsUpdate(Long id, ContentsRequestDto contentsRequestDto) {
        Contents contentsUpdate = contentsRepository.getById(id);
        contentsUpdate.setTitle(contentsRequestDto.getTitle());
        contentsUpdate.setImageUrl(contentsRequestDto.getImageUrl());
        contentsUpdate.setContents(contentsRequestDto.getContents());
        contentsRepository.save(contentsUpdate);
    }

    public void contentsDelete(Long id) {
        contentsRepository.deleteById(id);
    }
}
