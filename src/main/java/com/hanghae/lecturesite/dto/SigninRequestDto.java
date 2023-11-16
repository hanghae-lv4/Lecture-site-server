package com.hanghae.lecturesite.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SigninRequestDto {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
