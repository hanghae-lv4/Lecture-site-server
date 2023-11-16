package com.hanghae.lecturesite.service;

import com.hanghae.lecturesite.dto.SigninRequestDto;
import com.hanghae.lecturesite.dto.SignupRequestDto;
import com.hanghae.lecturesite.entity.Member;
import com.hanghae.lecturesite.jwt.JwtUtil;
import com.hanghae.lecturesite.repository.MemberRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // 비밀번호 검증을 위한 정규표현식
    private final String pwdRegex = "^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[!@#$%^&*()-_=+]).{8,15}$";

    public void signup(SignupRequestDto requestDto) {
        String email = requestDto.getEmail();

        // 이메일 중복 체크
        if (memberRepository.findByEmail(email).isPresent()) {
            // 이메일 중복 에러
            throw new IllegalArgumentException("중복된 이메일");
        }

        // 비밀번호 유효성 검사
        String pwd = requestDto.getPassword();
        if (!pwd.matches(pwdRegex)) {
            // 잘못된 비밀번호
            throw new IllegalArgumentException("유효하지 않은 비밀번호");
        }

        String encodedPwd = passwordEncoder.encode(pwd);
        Member member = new Member(requestDto);
        member.setPassword(encodedPwd);
        memberRepository.save(member);
    }

    public void signin(SigninRequestDto requestDto, HttpServletResponse res) {
        String email = requestDto.getEmail();
        System.out.println("email = " + email);
        String pwd = requestDto.getPassword();


        // Email로 멤버 찾기
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->
                // 없는 멤버일 경우 에러 반환
                new IllegalArgumentException("등록되지 않은 이메일입니다."));

        // Password 검사
        if (!passwordEncoder.matches(pwd, member.getPassword())) {
            // 비밀번호가 틀렸을 경우 에러 반환
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }

        // JWT 토큰 생성 후 쿠키에 넣기
        String token = jwtUtil.createToken(member.getEmail(), member.getRole());
        jwtUtil.addJwtToCookie(token, res);
    }
}
