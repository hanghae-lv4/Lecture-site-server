package com.hanghae.lecturesite.repository;


import com.hanghae.lecturesite.entity.Lecture;
import com.hanghae.lecturesite.entity.Tutor;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
}
