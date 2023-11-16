package com.hanghae.lecturesite.service;

import com.hanghae.lecturesite.entity.Lecture;
import com.hanghae.lecturesite.entity.Like;
import com.hanghae.lecturesite.entity.Member;
import com.hanghae.lecturesite.jwt.JwtUtil;
import com.hanghae.lecturesite.repository.LectureRepository;
import com.hanghae.lecturesite.repository.LikeRepository;
import com.hanghae.lecturesite.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final LectureRepository lectureRepository;
    private final JwtUtil jwtUtil;

    public LikeService(LikeRepository likeRepository, MemberRepository memberRepository,
        LectureRepository lectureRepository, JwtUtil jwtUtil) {
        this.likeRepository = likeRepository;
        this.memberRepository = memberRepository;
        this.lectureRepository = lectureRepository;
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    public String likeLecture(String jwt, Long lectureId) {

        String token = jwtUtil.substringToken(jwt);
        Claims claims = jwtUtil.getUserInfoFromToken(token);
        String memberEmail = claims.getSubject();


        Member member = memberRepository.findByEmail(memberEmail).orElseThrow(() ->
            new IllegalArgumentException("존재하지 않는 회원입니다.")
        );
        Long memberId = member.getId();

        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(() ->
            new IllegalArgumentException("존재하지 않는 강의입니다.")
        );

        // 이미 좋아요를 눌렀는지 확인
        Optional<Like> existingLike = Optional.ofNullable(
            likeRepository.findByMemberIdAndLectureId(memberId, lectureId));


        if (existingLike.isPresent()) {
            // 이미 좋아요를 눌렀으면 취소
            likeRepository.delete(existingLike.get());
            lecture.undoLikes();
            return "좋아요를 취소하였습니다.";
        } else {
            // 좋아요를 누르지 않았으면 생성
            Like like = new Like();
            like.setMember(member);
            like.setLecture(lecture);
            lecture.doLikes();
            likeRepository.save(like);
            return "좋아요가 등록되었습니다.";
        }
    }
}
