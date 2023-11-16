package com.hanghae.lecturesite.controller;

import com.hanghae.lecturesite.dto.CommentRequestDto;
import com.hanghae.lecturesite.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
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

//    @PutMapping("/{lecturesId}/comments/{commentsId}")
//    public ResponseEntity<String> updateComments(@PathVariable Long LecturesId,
//                                                 @PathVariable Long commentsId,
//                                                 @RequestBody CommentRequestDto commentRequestDto,
//                                                 HttpServletRequest request){
//        return new ResponseEntity<>(commentService.updateCommnets(LecturesId,commentsId, commentRequestDto, request),HttpStatus.OK);
//    }

}
