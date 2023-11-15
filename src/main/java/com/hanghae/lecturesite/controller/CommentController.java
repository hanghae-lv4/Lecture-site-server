package com.hanghae.lecturesite.controller;

import com.hanghae.lecturesite.entity.Comment;
import com.hanghae.lecturesite.entity.Lecture;
import com.hanghae.lecturesite.service.CommentService;
import com.hanghae.lecturesite.service.LectureService;
import java.nio.charset.StandardCharsets;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    // DI
    private final CommentService commentService;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }



    // 선택한 강의의 선택한 댓글 삭제
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<String> deleteComments(@PathVariable Long id) {
        String successMessage = commentService.deleteComments(id);
        return ResponseEntity.status(HttpStatus.CREATED)
            .header(
                HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE + ";charset=" + StandardCharsets.UTF_8)
            .body(successMessage);
    }
}
