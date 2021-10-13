package com.mini2.lost99.service;

import com.mini2.lost99.dto.UserRequestDto;
import com.mini2.lost99.model.User;
import com.mini2.lost99.repository.UserRepository;
import com.mini2.lost99.security.kakao.KakaoOAuth2;
import com.mini2.lost99.security.kakao.KakaoUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final KakaoOAuth2 kakaoOAuth2;


    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, KakaoOAuth2 kakaoOAuth2, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.kakaoOAuth2 = kakaoOAuth2;
    }

    public void registerUser(UserRequestDto userRequestDto) {
        // 회원 ID 중복 확인
        String username = userRequestDto.getUsername();
        String password = userRequestDto.getPassword();
        String passwordCheck = userRequestDto.getPasswordCheck();
        String email = userRequestDto.getEmail();

        System.out.println(username);
        System.out.println(password);
        System.out.println(passwordCheck);
        System.out.println(email);

        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }

        if (username.equals("") || password.equals("") || passwordCheck.equals("")) {
            throw new IllegalArgumentException("username || password || passwordCheck가 비어있습니다.");
        }

        if (password.length() < 6) {
            throw new IllegalArgumentException("password는 최소 6글자입니다.");
        }

        if (!password.equals(passwordCheck)) {
            throw new IllegalArgumentException("password와 passwordCheck가 다릅니다.");
        }
        // 패스워드 암호화
        password = passwordEncoder.encode(userRequestDto.getPassword());
        email = userRequestDto.getEmail();

        User user = new User(username, password, email);
        userRepository.save(user);
    }

    public void kakaoLogin(String authorizedCode) {
        // 카카오 OAuth2 를 통해 카카오 사용자 정보 조회
        KakaoUserInfo userInfo = kakaoOAuth2.getUserInfo(authorizedCode);
        Long kakaoId = userInfo.getId();
        String nickname = userInfo.getNickname();
        String email = userInfo.getEmail();


        // 우리 DB 에서 회원 Id 와 패스워드
        // 회원 Id = 카카오 nickname
        String username = nickname;
        // 패스워드 = 카카오 Id + ADMIN TOKEN
        String password = kakaoId + ADMIN_TOKEN;

        // DB 에 중복된 Kakao Id 가 있는지 확인
        User kakaoUser = userRepository.findByKakaoId(kakaoId)
                .orElse(null);

        // 카카오 정보로 회원가입
        if (kakaoUser == null) {
            // 패스워드 인코딩
            String encodedPassword = passwordEncoder.encode(password);

            kakaoUser = new User(nickname, encodedPassword, email, kakaoId);
            userRepository.save(kakaoUser);
        }

        // 로그인 처리
        Authentication kakaoUsernamePassword = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(kakaoUsernamePassword);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
