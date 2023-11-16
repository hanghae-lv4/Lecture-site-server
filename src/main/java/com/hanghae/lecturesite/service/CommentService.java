package com.hanghae.lecturesite.service;


import com.hanghae.lecturesite.dto.CommentRequestDto;
import com.hanghae.lecturesite.entity.Comment;
import com.hanghae.lecturesite.entity.Lecture;
import com.hanghae.lecturesite.entity.Member;
import com.hanghae.lecturesite.jwt.JwtUtil;
import com.hanghae.lecturesite.repository.CommentRepository;
import com.hanghae.lecturesite.repository.LectureRepository;
import com.hanghae.lecturesite.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.hanghae.lecturesite.entity.Comment;
import com.hanghae.lecturesite.repository.CommentRepository;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    private final LectureRepository lectureRepository;
    private final MemberRepository memberRepository;

    private final JwtUtil jwtUtil;

    public CommentService(CommentRepository commentRepository, LectureRepository lectureRepository, MemberRepository memberRepository, JwtUtil jwtUtil
    ) {
        this.commentRepository = commentRepository;
        this.lectureRepository = lectureRepository;
        this.memberRepository = memberRepository;
        this.jwtUtil = jwtUtil;
    }

    public String createComments(Long lecturesId, CommentRequestDto commentRequestDto, HttpServletRequest request) {
        Member member = findMember(request);

        Lecture lecture = lectureRepository.findById(lecturesId).orElseThrow(() ->
                new IllegalArgumentException("존재하지않는 강의입니다.")
        );
        Comment comment = new Comment(commentRequestDto);

        lecture.addComment(comment);
        comment.setMember(member);
        commentRepository.save(comment);
        lectureRepository.save(lecture);

        return "댓글 등록 성공";
    }

//    @Transactional
//    public String updateCommnets(Long lecturesId, Long commentsId, CommentRequestDto commentRequestDto, HttpServletRequest request) {
//        Member member = getAdminInfoFromToken(request);
//
//        Optional<Lecture> lecture = lectureRepository.findById(lecturesId);
//
//        if (lecture.isEmpty()) {
//            throw new NoSuchElementException("존재하지않는 강의입니다.");
//        }
//
//
//        if (comment.getMember().getId() == (member.getId())) {
//            comment.update(commentRequestDto);
//            return "댓글 수정 완료";
//        }
//    }


 

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

    private Member findMember(HttpServletRequest request) {
        // 토큰으로 유저 찾기
        String tokenValue = jwtUtil.getTokenFromRequest(request);
        String token = jwtUtil.substringToken(tokenValue);
        Claims info = jwtUtil.getUserInfoFromToken(token);
        Member member = memberRepository.findByEmail(info.getSubject()).orElseThrow(() ->
                new IllegalArgumentException("잘못된 유저입니다."));
        return member;
    }

}
