package com.example.teamproject_advice.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor // 모든 변수를 포함하는 생성자
@NoArgsConstructor  // 변수를 가지고 있지 않은 기본 생성자
@Data               // getter, setter
@Entity             // Entity : 독립체, jpa가 관리하는 클래스, 데이터베이스의 column과 연결시켜줌
@EntityListeners(AuditingEntityListener.class)
@ToString(exclude = {"boardList", "commentList", "roleList"})      // exclude : 제외됨, ToString을 제외할 것들
@Builder            // 클래스를 생성할 때 변수의 값을 쌓아서 넣을 수 있음
@Accessors(chain = true)        // setter 메서드를 쌓아서 사용 가능
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String account;
    private String password;
    private String status;
    private String phoneNumber;
    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;

    private String gender;
    private String color;
    private String myTemp;
    private String clotheStyle;
    private boolean enabled;

    @CreatedDate
    private LocalDateTime createdAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @LastModifiedBy
    private String updatedBy;

//    private Long userPhotoId;

    // 1 : N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Board> boardList = new ArrayList<>();

    // 1 : N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Comment> commentList = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id") )
    private List<Role> roleList = new ArrayList<>();
}
