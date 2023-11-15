package com.hanghae.lecturesite.service;


import com.hanghae.lecturesite.entity.Comment;
import com.hanghae.lecturesite.repository.CommentRepository;
import io.jsonwebtoken.Claims;
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
        // jwt 로직 임시 작성(확인 필요)
        // Claims claims = jwtUtil.getAdminInfoFromToken(jwt);
        // Long memberId = Long.parseLong(claims.getSubject());

        Comment comment = commentRepository.findById(id).orElseThrow(() ->
            new IllegalArgumentException("존재하지 않는 댓글입니다.")
        );

        // jwt 로직 임시 작성(확인 필요)
//        if (comment.getMember().getEmail().equals(memberId)) {
//            commentRepository.delete(comment);
//            return "댓글이 삭제되었습니다.";
//        }

        return "본인이 작성한 댓글만 삭제할 수 있습니다.";

    }
}
