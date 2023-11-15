package com.hanghae.lecturesite.service;

import com.hanghae.lecturesite.dto.LectureResponseDto;
import com.hanghae.lecturesite.entity.Lecture;
import com.hanghae.lecturesite.entity.LectureCategoryEnum;
import com.hanghae.lecturesite.repository.LectureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LectureService {
    private final LectureRepository lectureRepository;

    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    public List<LectureResponseDto> getLectureByCategory(String category, String sort, String orderBy) {
        List<Lecture> lectures = lectureRepository.findByCategory(category);
        if (Objects.equals(sort, "title") && Objects.equals(orderBy, "Desc")) {
            lectures = lectureRepository.findByOrderByTitleDesc();
        } else if (Objects.equals(sort, "title") && Objects.equals(orderBy, "Asc")) {
            lectures = lectureRepository.findByOrderByTitleAsc();
        } else if (Objects.equals(sort, "price") && Objects.equals(orderBy, "Desc")) {
            lectures = lectureRepository.findByOrderByPriceDesc();
        } else if (Objects.equals(sort, "price") && Objects.equals(orderBy, "Asc")) {
            lectures = lectureRepository.findByOrderByPriceAsc();
        } else if (Objects.equals(sort, "regDate") && Objects.equals(orderBy, "Desc")) {
            lectures = lectureRepository.findByOrderByRegDateDesc();
        } else if (Objects.equals(sort, "regDate") && Objects.equals(orderBy, "Asc")) {
            lectures = lectureRepository.findByOrderByRegDateAsc();
        }
        return lectures.stream().map(LectureResponseDto::new).collect(Collectors.toList());

    }
}
