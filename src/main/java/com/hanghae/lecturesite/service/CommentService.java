package com.hanghae.lecturesite.service;


import com.hanghae.lecturesite.dto.CommentRequestDto;
import com.hanghae.lecturesite.dto.CommentResponseDto;
import com.hanghae.lecturesite.entity.Comment;
import com.hanghae.lecturesite.entity.Lecture;
import com.hanghae.lecturesite.repository.CommentRepository;
import com.hanghae.lecturesite.repository.LectureRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import com.hanghae.lecturesite.entity.Comment;
import com.hanghae.lecturesite.repository.CommentRepository;
import io.jsonwebtoken.Claims;
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


 

    // 선택한 강의의 선택한 댓글 삭제
    public String deleteComments(Long lectureId, Long commentId, String jwt) {
        // jwt 로직 임시 작성(확인 필요)
        // Claims claims = jwtUtil.getAdminInfoFromToken(jwt);
        // Long memberId = Long.parseLong(claims.getSubject());


        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
            new IllegalArgumentException("존재하지 않는 댓글입니다.")
        );

        // jwt 로직 임시 작성(확인 필요)
//        if (comment.getMember().getEmail().equals(memberId)) {
//            commentRepository.delete(comment);
//            return "댓글이 삭제되었습니다.";
//        }

        return "본인이 작성한 댓글만 삭제할 수 있습니다.";


    }
}
