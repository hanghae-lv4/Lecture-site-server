package com.hanghae.lecturesite.controller;

import com.hanghae.lecturesite.dto.LectureResponseDto;
import com.hanghae.lecturesite.dto.RegistLectureRequestDto;
import com.hanghae.lecturesite.service.LectureService;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LectureController {

    // DI
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


    // 강의 등록
    @PostMapping("/lectures")
    public ResponseEntity<String> registerLecture(@RequestBody RegistLectureRequestDto requestDto) {
        String successMessage = lectureService.registerLecture(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
            .header(
                HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE + ";charset=" + StandardCharsets.UTF_8)
            .body(successMessage);
    }

    @GetMapping("/lectures/{id}")
    public LectureResponseDto getLectureById(@PathVariable Long id) {
        return lectureService.getLecture(id);
    }

}
