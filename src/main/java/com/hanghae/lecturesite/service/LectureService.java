package com.hanghae.lecturesite.service;

import com.hanghae.lecturesite.dto.LectureResponseDto;
import com.hanghae.lecturesite.dto.RegistLectureRequestDto;
import com.hanghae.lecturesite.entity.Lecture;
import com.hanghae.lecturesite.entity.Tutor;
import com.hanghae.lecturesite.repository.LectureRepository;
import com.hanghae.lecturesite.repository.TutorRepository;
import org.springframework.stereotype.Service;

@Service
public class LectureService {

    // DI
    private final LectureRepository lectureRepository;
    private final TutorRepository tutorRepository;

    public LectureService(LectureRepository lectureRepository, TutorRepository tutorRepository) {
        this.lectureRepository = lectureRepository;
        this.tutorRepository = tutorRepository;
    }

    // 강의 등록
    public String registerLecture(RegistLectureRequestDto requestDto) {
        Lecture lecture = new Lecture();
        Tutor tutor = findTutor(requestDto.getTutorId());
        lecture.setTitle(requestDto.getTitle());
        lecture.setPrice(requestDto.getPrice());
        lecture.setIntro(requestDto.getIntro());
        lecture.setCategory(requestDto.getCategory());
        lecture.setTutor(tutor);

        lectureRepository.save(lecture);
        return "강의가 등록 되었습니다.";
    }

    // 선택한 강의 조회
    public LectureResponseDto getLecture(Long id) {
        Lecture lecture = findLecture(id);
        return new LectureResponseDto(lecture);
    }



    // ID로 Lecture 찾기
    private Lecture findLecture(Long id) {
        return lectureRepository.findById(id).orElseThrow(() ->
            new IllegalArgumentException("선택한 강의는 존재하지 않습니다.")
        );
    }

    // ID로 Tutor 찾기
    private Tutor findTutor(Long tutorId) {
        return tutorRepository.findById(tutorId).orElseThrow(() ->
            new IllegalArgumentException("존재하지 않는 강사입니다.")
        );
    }
}
