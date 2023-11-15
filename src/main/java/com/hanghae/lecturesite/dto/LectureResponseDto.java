package com.hanghae.lecturesite.dto;

import com.hanghae.lecturesite.entity.Lecture;
import com.hanghae.lecturesite.entity.LectureCategoryEnum;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LectureResponseDto {
    private String title;
    private Integer price;
    private String intro;
    private LectureCategoryEnum category;
    private LocalDateTime regDate;

    public LectureResponseDto(Lecture lecture){
        this.title = lecture.getTitle();
        this.price = lecture.getPrice();
        this.intro = lecture.getIntro();
        this.category = lecture.getCategory();
        this.regDate = lecture.getRegDate();
    }

}
