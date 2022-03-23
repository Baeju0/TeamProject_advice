package com.example.teamproject_advice.repository;

import com.example.teamproject_advice.model.entity.Board;
import com.example.teamproject_advice.model.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByCommentContaining(String search, Pageable pageable);
    Page<Comment> findByBoard(Board board, Pageable pageable);
}
