package com.hanghae.lecturesite.dto;

import com.hanghae.lecturesite.entity.Tutor;
import lombok.Getter;

@Getter
public class TutorResponseDto {
    private String name;
    private Integer career;
    private String company;
    private String phone;
    private String intro;

    public TutorResponseDto(Tutor tutor) {
        this.name = tutor.getName();
        this.career = tutor.getCareer();
        this.company = tutor.getCompany();
        this.phone = tutor.getPhone();
        this.intro = tutor.getIntro();
    }

}
