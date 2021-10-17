package com.mini2.lost99.service;

import com.mini2.lost99.dto.ContentsRequestDto;
import com.mini2.lost99.dto.ContentsResponseDto;
import com.mini2.lost99.model.Contents;
import com.mini2.lost99.repository.ContentsRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

        for(Contents content : contents){
            ContentsResponseDto contentsResponseDto = new ContentsResponseDto(
                    content.getId(),
                    content.getTitle(),
                    content.getUsername(),
                    content.getPhonenumber(),
                    content.getContents(),
                    content.getImageUrl(),
                    content.getLocation(),
                    content.getCreatedAt(),
                    content.getModifiedAt()
            );

            contentsResponseDtos.add(contentsResponseDto);
        }

        return contentsResponseDtos;
    }

    @Transactional
    public Contents contentsSave(ContentsRequestDto contentsRequestDto) {
        Contents contents =new Contents(contentsRequestDto);
        contentsRepository.save(contents);
        return contents;
    }

    @Transactional
    public void contentsUpdate(Long id, ContentsRequestDto contentsRequestDto) {
        Contents contentsUpdate = contentsRepository.getById(id);
        contentsUpdate.setTitle(contentsRequestDto.getTitle());
        contentsUpdate.setContents(contentsRequestDto.getContents());
        contentsUpdate.setLocation(contentsRequestDto.getLocation());
        contentsUpdate.setPhonenumber(contentsRequestDto.getPhonenumber());
        contentsUpdate.setImageUrl(contentsRequestDto.getImageUrl());
        contentsRepository.save(contentsUpdate);
    }

    public void contentsDelete(Long id) {
        contentsRepository.deleteById(id);
    }

    public ContentsResponseDto readContent(Long id) {


       Contents content = contentsRepository.findById(id).orElseThrow(
               () -> new IllegalArgumentException("해당 하는 글이 없습니다."));

        ContentsResponseDto contentsResponseDto = new ContentsResponseDto(
                content.getId(),
                content.getTitle(),
                content.getUsername(),
                content.getPhonenumber(),
                content.getContents(),
                content.getImageUrl(),
                content.getLocation(),
                content.getCreatedAt(),
                content.getModifiedAt()
        );


        return contentsResponseDto;
    }
}
