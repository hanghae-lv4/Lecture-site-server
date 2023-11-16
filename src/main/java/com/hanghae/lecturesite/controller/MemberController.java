package com.hanghae.lecturesite.controller;

import com.hanghae.lecturesite.dto.SigninRequestDto;
import com.hanghae.lecturesite.dto.SignupRequestDto;
import com.hanghae.lecturesite.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid SignupRequestDto requestDto) {
        memberService.signup(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("회원가입 완료");
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signin(@RequestBody @Valid SigninRequestDto requestDto, HttpServletResponse res) {
        try {
            memberService.signin(requestDto, res);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("로그인 성공");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

