package com.example.teamproject_advice.record;

import com.example.teamproject_advice.model.entity.Board;
import com.example.teamproject_advice.model.entity.Comment;
import com.example.teamproject_advice.model.entity.User;
import com.example.teamproject_advice.repository.BoardRepository;
import com.example.teamproject_advice.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class BoardCommentService {

    @NonNull
    private BoardRepository boardrepository;
    @NonNull
    private BoardCommentRepository boardCommentRepository;
    @NonNull
    private UserRepository userRepository;

    // 댓글 등록하기
    public Comment createComment(Comment comment, User user, long board) {
        Optional<Board> board1 = this.boardrepository.findById(board);
        board1.ifPresent(co -> {
//            comment.changeBoard(co);      list 연결 뭐 있었는데 이미 지워서 복구를 할 수 없었다.
        });
//        comment.changeAuthor(user);
        return this.boardCommentRepository.save(comment);
    }

    // 댓글 리스트?
    @Transactional(readOnly = true)
    public List<Comment> ListBoardComment(long board) {
//        return this.boardCommentRepository.getBoardCommentOfBoard(board);
        return null; // 에러 방지용 추가(동건)
    }

    // 댓글 삭제
    @Transactional
    public List<Comment> DeleteBoardComment(long commentNo, long board) {
        this.boardCommentRepository.deleteById(commentNo);
//        return this.boardCommentRepository.getBoardCommentOfBoard(board);
        return null; // 에러 방지용 추가(동건)
    }

    // 댓글 수정
    @Transactional
    public List<Comment> ModifyComments(Comment boardComment, long commentNo, long board) {
        Optional<Comment> modifycomment = this.boardCommentRepository.findById(commentNo);
        modifycomment.ifPresent(origin -> {
            origin.setComment(boardComment.getComment());
            this.boardCommentRepository.save(origin);
        });

//        return this.boardCommentRepository.getBoardCommentOfBoard(board);
        return null; // 에러 방지용 추가(동건)
    }
}


