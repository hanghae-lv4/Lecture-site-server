package com.hanghae.lecturesite.controller;

import com.hanghae.lecturesite.dto.CommentRequestDto;
import com.hanghae.lecturesite.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lectures")
public class CommentController {
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    private final CommentService commentService;


    @PostMapping("/{lecturesId}/comments")
    public ResponseEntity<String> createComments(@PathVariable Long lecturesId, @RequestBody CommentRequestDto commentRequestDto) {
        return new ResponseEntity<>(commentService.createComments(lecturesId, commentRequestDto), HttpStatus.OK);

    }


}
