package com.hanghae.lecturesite.controller;


import com.hanghae.lecturesite.dto.CommentRequestDto;
import com.hanghae.lecturesite.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.hanghae.lecturesite.entity.Comment;
import com.hanghae.lecturesite.entity.Lecture;
import com.hanghae.lecturesite.service.CommentService;
import com.hanghae.lecturesite.service.LectureService;
import java.nio.charset.StandardCharsets;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lectures")
public class CommentController {

    // DI
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{lecturesId}/comments")
    public ResponseEntity<String> createComments(@PathVariable Long lecturesId, @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest req) {
        return new ResponseEntity<>(commentService.createComments(lecturesId, commentRequestDto, req), HttpStatus.OK);
    }

    @PutMapping("/{lecturesId}/comments/{commentsId}")
    public ResponseEntity<String> updateComments(@PathVariable Long lecturesId,
                                                 @PathVariable Long commentsId,
                                                 @RequestBody CommentRequestDto commentRequestDto,
                                                 HttpServletRequest request){
        return new ResponseEntity<>(commentService.updateComments(lecturesId, commentsId, commentRequestDto, request),HttpStatus.OK);
    }

    // 선택한 강의의 선택한 댓글 삭제
    @DeleteMapping("/{lecturesId}/comments/{commentsId}")
    public ResponseEntity<String> deleteComments(@PathVariable Long lecturesId, @PathVariable Long commentsId, @CookieValue(value = "Authorization", required = false) String jwt
    ) {
        String successMessage = commentService.deleteComments(lecturesId, commentsId, jwt);
        return ResponseEntity.status(HttpStatus.CREATED)
            .header(
                HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE + ";charset=" + StandardCharsets.UTF_8)
            .body(successMessage);
    }
}
