package com.mini2.lost99.controller;

import com.mini2.lost99.dto.UserRequestDto;
import com.mini2.lost99.model.User;
import com.mini2.lost99.repository.UserRepository;
import com.mini2.lost99.security.JwtTokenProvider;
import com.mini2.lost99.service.KakaoUserService;
import com.mini2.lost99.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final KakaoUserService kakaoUserService;
    private final UserRepository userRepository;


    // 회원 가입 요청 처리
    @PostMapping("/api/signup")
    public void registerUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        userService.registerUser(userRequestDto);
    }

    @PostMapping("/api/login")
    public String login(@RequestBody UserRequestDto requestDto) {
        User user = userRepository.findByUsername(requestDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 유저입니다."));
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 잘못되었습니다.");
        }
        return jwtTokenProvider.createToken(user.getUsername());
    }


    @GetMapping("/api/kakao/callback")
    public void kakaoLogin(String code) {
        // authorizedCode: 카카오 서버로부터 받은 인가 코드
        userService.kakaoLogin(code);
    }
}
