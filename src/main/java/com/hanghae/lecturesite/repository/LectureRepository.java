package com.hanghae.lecturesite.repository;

import com.hanghae.lecturesite.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    List<Lecture> findByCategory(String category);

    List<Lecture> findByOrderByTitleDesc();

    List<Lecture> findByOrderByTitleAsc();

    List<Lecture> findByOrderByPriceDesc();

    List<Lecture> findByOrderByPriceAsc();

    List<Lecture> findByOrderByRegDateDesc();

    List<Lecture> findByOrderByRegDateAsc();

}
