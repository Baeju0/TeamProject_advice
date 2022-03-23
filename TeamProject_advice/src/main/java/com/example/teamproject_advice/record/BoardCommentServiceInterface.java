package com.example.teamproject_advice.record;

import com.example.teamproject_advice.model.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardCommentServiceInterface {
    Page<Board> boardListPage(Pageable pageable);
    int boardListLastPage(int size);
    Pageable checkPageable(Pageable pageable);
//    Board findById(Long id);
    List<Integer> paging(Pageable pageable);
    int returnPageNumber(Long id, int size);
}
