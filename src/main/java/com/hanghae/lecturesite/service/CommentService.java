package com.hanghae.lecturesite.service;

import com.hanghae.lecturesite.dto.CommentRequestDto;
import com.hanghae.lecturesite.dto.CommentResponseDto;
import com.hanghae.lecturesite.entity.Comment;
import com.hanghae.lecturesite.entity.Lecture;
import com.hanghae.lecturesite.repository.CommentRepository;
import com.hanghae.lecturesite.repository.LectureRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    private final LectureRepository lectureRepository;
    public CommentService(CommentRepository commentRepository,LectureRepository lectureRepository
    ) {
        this.commentRepository = commentRepository;
        this.lectureRepository = lectureRepository;
    }

    public String createComments(Long lecturesId, CommentRequestDto commentRequestDto) {
        Lecture lecture  = lectureRepository.findById(lecturesId).orElseThrow(()->
               new IllegalArgumentException("존재하지않는 강의입니다.")
        );
        Comment comment = new Comment(commentRequestDto);

        lecture.addComment(comment);
        lectureRepository.save(lecture);

        return "댓글 등록 성공";


    }
}
