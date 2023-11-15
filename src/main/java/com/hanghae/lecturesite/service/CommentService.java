package com.hanghae.lecturesite.service;


import com.hanghae.lecturesite.entity.Comment;
import com.hanghae.lecturesite.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    // DI
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    // 선택한 강의의 선택한 댓글 삭제
    public String deleteComments(Long id, String jwt) {
        // jwt 로직 작성 필요
        Comment comment = commentRepository.findById(id).orElseThrow(() ->
            new IllegalArgumentException("존재하지 않는 댓글입니다.")
            );
        commentRepository.delete(comment);
        return "댓글이 삭제되었습니다.";
    }
}
