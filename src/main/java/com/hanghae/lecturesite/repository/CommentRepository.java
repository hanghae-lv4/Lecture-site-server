package com.hanghae.lecturesite.repository;

import com.hanghae.lecturesite.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
