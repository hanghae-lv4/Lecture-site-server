package com.hanghae.lecturesite.repository;

import com.hanghae.lecturesite.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByLecturesIdAndCommentsId(Long lectursId, Long commentsId);
}
