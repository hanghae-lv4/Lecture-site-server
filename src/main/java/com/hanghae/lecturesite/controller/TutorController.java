package com.hanghae.lecturesite.controller;

import com.hanghae.lecturesite.dto.TutorRequestDto;
import com.hanghae.lecturesite.dto.TutorResponseDto;
import com.hanghae.lecturesite.service.TutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TutorController {
    private final TutorService tutorService;

    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @PostMapping("/tutors")
    public ResponseEntity<TutorResponseDto> createTutor(@RequestBody TutorRequestDto requestDto){
        return new ResponseEntity<>(tutorService.createTutor(requestDto), HttpStatus.OK);
    }


}
