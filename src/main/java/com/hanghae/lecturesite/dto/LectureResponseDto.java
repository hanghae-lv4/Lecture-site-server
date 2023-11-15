package com.hanghae.lecturesite.dto;

import com.hanghae.lecturesite.entity.Lecture;
import com.hanghae.lecturesite.entity.LectureCategoryEnum;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LectureResponseDto {
    private String title;
    private Integer price;
    private LectureCategoryEnum category;
    private String lectureIntro;
    private LocalDateTime regDate;
    private String name;
    private Long career;
    private String company;
    private String tutorIntro;


    public LectureResponseDto(Lecture lecture) {
        this.title = lecture.getTitle();
        this.lectureIntro = lecture.getIntro();
        this.category = lecture.getCategory();
        this.price = lecture.getPrice();
        this.regDate = lecture.getRegDate();
        this.name = lecture.getTutor().getName();
        this.career = lecture.getTutor().getCareer();
        this.company = lecture.getTutor().getCompany();
        this.tutorIntro = lecture.getTutor().getIntro();
    }
}