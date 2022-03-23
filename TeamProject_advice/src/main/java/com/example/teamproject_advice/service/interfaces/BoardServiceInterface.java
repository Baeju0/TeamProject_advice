package com.example.teamproject_advice.service.interfaces;

import com.example.teamproject_advice.model.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

// Service interface : 오타 방지, (메서드 요약집)

public interface BoardServiceInterface {
    Page<Board> boardListPage(Pageable pageable);
    Page<Board> searchBoardPage(String search, Pageable pageable);
    void boardWrite(String account, Board board);

    String BoardDelete(Long id);

    Pageable checkPageable(Pageable pageable);
    Board findById(Long id);
    List<Integer> paging(Pageable pageable, int totalPage);

    int boardListLastPage(int size, String search);
    int returnPageNumber(Long id, int size);
}
