package com.hanghae.lecturesite.dto;

import com.hanghae.lecturesite.entity.LectureCategoryEnum;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RegistLectureRequestDto {
    private String title;
    private Integer price;
    private String intro;
    private LectureCategoryEnum category;
    private Long tutorId;
}
