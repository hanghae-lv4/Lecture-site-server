package com.hanghae.lecturesite.filter;

import com.hanghae.lecturesite.entity.Member;
import com.hanghae.lecturesite.entity.MemberRoleEnum;
import com.hanghae.lecturesite.jwt.JwtUtil;
import com.hanghae.lecturesite.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Objects;

@Slf4j(topic = "AuthFilter")
//@Component
@Order(1)
public class AuthFilter implements Filter {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    public AuthFilter(MemberRepository memberRepository, JwtUtil jwtUtil) {
        this.memberRepository = memberRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String url = httpServletRequest.getRequestURI();

        if (StringUtils.hasText(url) &&
                (url.startsWith("/signup") || url.startsWith("/signin"))
        ) {
            // 회원가입, 로그인 관련 API 는 인증 필요없이 요청 진행
            chain.doFilter(request, response); // 다음 Filter 로 이동
        } else {
            // 나머지 API 요청은 인증 처리 진행
            // 토큰 확인
            String tokenValue = jwtUtil.getTokenFromRequest(httpServletRequest);

            if (StringUtils.hasText(tokenValue)) { // 토큰이 존재하면 검증 시작
                // JWT 토큰 substring
                String token = jwtUtil.substringToken(tokenValue);

                // 토큰 검증
                if (!jwtUtil.validateToken(token)) {
                    throw new IllegalArgumentException("Token Error");
                }

                // 토큰에서 사용자 정보 가져오기
                Claims info = jwtUtil.getUserInfoFromToken(token);

                Member member = memberRepository.findByEmail(info.getSubject()).orElseThrow(() ->
                        new NullPointerException("Not Found User")
                );

                // 강사등록 및 강의등록은 ROLE_ADMIN 계정만 가능하도록 설정
                if (StringUtils.hasText(url) && (url.equals("/lectures") || url.equals("/tutors"))) {
                    // JWT 토큰에서 사용자의 ROLE을 가져옴
                    String auth = (String) info.get(JwtUtil.AUTHORIZATION_KEY);

                    // 사용자의 권한을 ADMIN과 비교
                    if (!Objects.equals(MemberRoleEnum.valueOf(auth).getAuthority(), MemberRoleEnum.ADMIN.getAuthority())) {
                        throw new IllegalArgumentException("ADMIN만 접근 가능한 기능입니다.");
                    }
                }

                request.setAttribute("member", member);
                chain.doFilter(request, response); // 다음 Filter 로 이동
            } else {
                throw new IllegalArgumentException("Not Found Token");
            }
        }
    }
}