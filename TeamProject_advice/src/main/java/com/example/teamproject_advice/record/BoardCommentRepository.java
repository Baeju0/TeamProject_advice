package com.example.teamproject_advice.record;

import com.example.teamproject_advice.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardCommentRepository extends JpaRepository<Comment, Long> {
//    public List<Comment> getBoardCommentOfBoard(@Param("board") Long board);
    // 위에 @Query가 있었다. 그래서 이런 메서드 이름이 가능했나보다.
}
