package com.hanghae.lecturesite.service;

import com.hanghae.lecturesite.entity.Like;
import com.hanghae.lecturesite.repository.LectureRepository;
import com.hanghae.lecturesite.repository.LikeRepository;
import com.hanghae.lecturesite.repository.MemberRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final LectureRepository lectureRepository;

    public LikeService(LikeRepository likeRepository, MemberRepository memberRepository,
        LectureRepository lectureRepository) {
        this.likeRepository = likeRepository;
        this.memberRepository = memberRepository;
        this.lectureRepository = lectureRepository;
    }

    @Transactional
    public String likeLecture(String jwt, Long lectureId) {

        // memberId 는 jwt로 다시 받아와야 함
        Long memberId = 1L;

        // 이미 좋아요를 눌렀는지 확인
        Optional<Like> existingLike = Optional.ofNullable(
            likeRepository.findByMemberIdAndLectureId(memberId, lectureId));


        if (existingLike.isPresent()) {
            // 이미 좋아요를 눌렀으면 취소
            likeRepository.delete(existingLike.get());
            return "좋아요를 취소하였습니다.";
        } else {
            // 좋아요를 누르지 않았으면 생성
            Like like = new Like();
            like.setMember(memberRepository.findById(memberId).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 회원입니다.")
            ));
            like.setLecture(lectureRepository.findById(lectureId).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 강의입니다.")
            ));
            likeRepository.save(like);
            return "좋아요가 등록되었습니다.";
        }
    }
}
