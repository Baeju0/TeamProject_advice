package com.example.teamproject_advice.record;

import com.example.teamproject_advice.TeamProjectAdviceApplicationTests;
import com.example.teamproject_advice.model.entity.Board;
import com.example.teamproject_advice.model.entity.Comment;
import com.example.teamproject_advice.repository.BoardRepository;
import com.example.teamproject_advice.repository.CommentRepository;
import com.example.teamproject_advice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class CommentRepositoryTest extends TeamProjectAdviceApplicationTests {

    @Autowired
    private CommentRepository boardCommentRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserRepository userRepository;

    @Test //생성
    public void create() {
        List<Board> boardList = boardRepository.findAll();

        Comment comm1 = Comment.builder()
                .user(userRepository.findAll().get(0))
                .board(boardList.get(boardList.size()-1))
                .comment("안할래")
                .createdAt(LocalDateTime.now())
                .createdBy("admin")
                .build();


        Comment newComment = boardCommentRepository.save(comm1);
        System.out.println(newComment);
    }

    @Test
    public void read() {
        Optional<Comment> boardComment = boardCommentRepository.findById(2L);

        boardComment.ifPresent(bc -> {
            System.out.println("댓글을 쓴 게시글 : "+bc.getBoard().getTitle());
            System.out.println("댓글 내용 : "+bc.getComment());
            System.out.println("댓글을 쓴 유저 : " + bc.getUser().getAccount());
        });
    }

//    @Test //읽기
//    public void read() {
//
//        List<BoardComment> boardCommentsList = boardCommentRepository.findAll();
//
//        for (BoardComment boardComment : boardCommentList) {
//
//            System.out.println(item.getId());
//            System.out.println(item.getStatus());
//            System.out.println(item.getName());
//            System.out.println(item.getTitle());
//            System.out.println(item.getContent());
//            System.out.println(item.getPrice());
//            System.out.println(item.getBrand_name());
//            System.out.println(item.getBrand_name());
//        }
//    }
//
//    @Test //수정
//    public void update() {
//        Optional<User> user = userRepository.findById(2L);
//
//        user.ifPresent(u -> {
//            u.setAccount("jy");
//            u.setPassword("1234");
//            u.setUpdatedAt(LocalDateTime.now());
//            u.setUpdatedBy("admin");
//
//            userRepository.save(u);
//
//        });
//
//    }
//
//    @Test //삭제
//    public void delete() {
//        Optional<User> user = userRepository.findById(2L);
//
//        user.ifPresent(u -> {
//            userRepository.delete(u);
//        });
//    }
}