package com.hanghae.lecturesite.dto;

import com.hanghae.lecturesite.entity.LectureCategoryEnum;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LectureRequestDto {
    private String title;
    private Integer price;
    private String intro;
    private LectureCategoryEnum category;
}
