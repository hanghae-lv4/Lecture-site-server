package com.hanghae.lecturesite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CSRF disable
        http.csrf((csrf) -> csrf.disable());

        // 요청 URL 제어
        http.authorizeHttpRequests((authorizeHttpRequests) ->
                authorizeHttpRequests
                        .requestMatchers("/signup").permitAll() // 회원가입시 인증 미필요
                        .requestMatchers("/signin").permitAll() // 로그인시 인증 미필요
                        .requestMatchers("/lectures").hasRole("ADMIN")
                        .requestMatchers("/tutors").hasRole("ADMIN")
                        .anyRequest().authenticated() // 그 외 모든 요청엔 인증 필요

        );
        return http.build();
    }
}
