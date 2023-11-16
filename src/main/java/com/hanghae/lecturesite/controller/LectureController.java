package com.hanghae.lecturesite.controller;

import com.hanghae.lecturesite.dto.LectureResponseDto;
import com.hanghae.lecturesite.entity.LectureCategoryEnum;
import com.hanghae.lecturesite.service.LectureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LectureController {
    private final LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @GetMapping("/lecture")
    public ResponseEntity<List<LectureResponseDto>> getLectureByCategory(@RequestParam("category") String category,
                                                                         @RequestParam("sort") String sort,
                                                                         @RequestParam("orderBy") String orderBy){
        return new ResponseEntity<>(lectureService.getLectureByCategory(category, sort, orderBy), HttpStatus.OK);
    }
}
