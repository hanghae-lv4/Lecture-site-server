package com.hanghae.lecturesite.repository;

import com.hanghae.lecturesite.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Like findByMemberIdAndLectureId(Long memberId, Long lectureId);
}
