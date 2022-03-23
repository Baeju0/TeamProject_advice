package com.example.teamproject_advice.service.interfaces;

import com.example.teamproject_advice.model.entity.Board;
import com.example.teamproject_advice.model.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentServiceInterface {
    Page<Comment> commentListPage(Board board, int page);
    void commentWrite(Long boardId, Long commentId, String account, String comment);

    String commentDelete(Long id);

    Pageable checkPageable(Pageable pageable);
    Comment findById(Long id);

    int commentListLastPage(Board board, String search);
    int returnPageNumber(Long id, int size);


    // spring security
    List<Boolean> checkCommentAccount(Page<Comment> page);
}
