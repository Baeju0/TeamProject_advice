package com.example.teamproject_advice.repository;

import com.example.teamproject_advice.TeamProjectAdviceApplicationTests;
import com.example.teamproject_advice.model.entity.Board;
import com.example.teamproject_advice.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BoardRepositoryTest extends TeamProjectAdviceApplicationTests {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {
        for (int i=1; i<100; i++) {
            Board board = Board.builder()
                            .title(i + "번째 게시글")
                            .content(i + "번째 게시글입니다.")
                            .viewCount(1L)
                            .registeredAt(LocalDateTime.now())
                            .createdAt(LocalDateTime.now())
                            .createdBy("admin")
                            .user(userRepository.findAll().get(0))
                            .build();

            boardRepository.save(board);
        }
    }

    @Test
    public void read() {
//        System.out.println(userRepository.findAll().get(0));

        List<User> list = userRepository.findAll();
        list.get(0);

        // [User1, User2, User3, User4].get(0)

        for (User u : list) {
            System.out.println(u);
        }
    }

    @Test
    public void readTest() {
//        List<Board> list = boardRepository.findByIdLessThanAndIdGreaterThanEqualOrderByIdDesc(0L, 10L);
//        for (Board board : list) {
//            System.out.println(board.getTitle());
//        }
//
//        if (list.isEmpty()) {
//            System.out.println("값이 없습ㄴ디ㅏㄴ");
//        }

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"));
        Page<Board> boardPage = boardRepository.findAll(pageRequest);

        for (Board board : boardPage) {
            System.out.println("board title : " + board.getTitle());
        }
    }

    @Test
    public void deleteAll() {
        List<Board> list = boardRepository.findAll();

        for (Board board : list) {
            boardRepository.delete(board);
        }
    }

    @Test
    public void pageTest() {
        // 페이지 유무 확인
//        Page<Board> pageBoardList = boardRepository.findAll(PageRequest.of(0, 10, Sort.Direction.DESC, "id"));
//        System.out.println(pageBoardList);
//        System.out.println(pageBoardList.isEmpty());

        // 총 페이지 개수 확인
        int test = boardRepository.findAll(PageRequest.of(0, 10, Sort.Direction.DESC, "id")).getTotalPages();
        System.out.println(test);
    }

    @Test
    public void searchTest() {
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "id");
        Page<Board> boardPage = boardRepository.findByTitleContaining("번째", pageable);
        System.out.println(boardPage.getTotalPages());
    }

    @Test
    public void offsetTest() {
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "id");
        System.out.println(pageable.getOffset());

    }
}
