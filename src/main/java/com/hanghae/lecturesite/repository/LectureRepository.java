package com.hanghae.lecturesite.repository;

import com.hanghae.lecturesite.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {

}
