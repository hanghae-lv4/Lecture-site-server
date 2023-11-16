package com.hanghae.lecturesite.repository;

import com.hanghae.lecturesite.entity.Lecture;
import com.hanghae.lecturesite.entity.LectureCategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    List<Lecture> findByCategory(LectureCategoryEnum category);

    List<Lecture> findLectureByOrderByTitleDesc();

    List<Lecture> findLectureByOrderByTitleAsc();

    List<Lecture> findLectureByOrderByPriceDesc();

    List<Lecture> findLectureByOrderByPriceAsc();

    List<Lecture> findLectureByOrderByRegDateDesc();

    List<Lecture> findLectureByOrderByRegDateAsc();

}
