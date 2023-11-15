package com.hanghae.lecturesite.controller;

import com.hanghae.lecturesite.service.LikeService;
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
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) { this.likeService = likeService; }


    @DeleteMapping("/lectures/{id}/like")
    public ResponseEntity<String> likeLecture(@PathVariable Long id, @CookieValue(value = "Authorization", required = false) String jwt
    ) {
        String successMessage = likeService.likeLecture(jwt, id);
        return ResponseEntity.status(HttpStatus.OK)
            .header(
                HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE + ";charset=" + StandardCharsets.UTF_8)
            .body(successMessage);
    }
}
