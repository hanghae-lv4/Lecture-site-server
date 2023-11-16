package com.hanghae.lecturesite.service;

import com.hanghae.lecturesite.dto.TutorRequestDto;
import com.hanghae.lecturesite.dto.TutorResponseDto;
import com.hanghae.lecturesite.entity.Tutor;
import com.hanghae.lecturesite.repository.TutorRepository;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    private final TutorRepository tutorRepository;

    public TutorService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    public TutorResponseDto createTutor(TutorRequestDto requestDto) {
        Tutor tutor = new Tutor(requestDto);
        return new TutorResponseDto(tutorRepository.save(tutor));
    }
}
