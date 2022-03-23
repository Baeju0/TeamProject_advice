package com.example.teamproject_advice.model.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// 역할 : 테이블에 대응하는(column) 클래스

@AllArgsConstructor                                 // 모든 변수를 설정하는 생성자
@NoArgsConstructor                                  // 변수를 포함하지 않는 생성자
@Entity                                             // JPA에서 클래스를 관리하도록 표시 (DB의 테이블과 연동), entity : 실제의
@Data                                               // 모든 변수의 getter, setter를 만들어줌
@ToString(exclude = {"user", "commentList"})        // exclude : 제외된, 안에 표기된 친구는 ToString에 포함되지 않음
@Builder                                            // 객체를 생성할 때 변수를 쌓아서 입력 가능.
@Accessors(chain = true)                            // setter를 쌓아서 입력 가능
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private Long viewCount;

    private LocalDateTime registeredAt;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    @ManyToOne      // N : 1
    private User user;

    // board : BoardComment > 1 : N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board")
    private List<Comment> commentList = new ArrayList<>();
}
