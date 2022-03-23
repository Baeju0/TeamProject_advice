package com.example.teamproject_advice.model.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor // 모든 변수를 포함하는 생성자
@NoArgsConstructor  // 변수를 가지고 있지 않은 기본 생성자
@Data               // getter, setter
@Entity             // Entity : 독립체, jpa가 관리하는 클래스, 데이터베이스의 column과 연결시켜줌
@ToString(exclude = {"userList"})      // exclude : 제외됨, ToString을 제외할 것들
@Builder            // 클래스를 생성할 때 변수의 값을 쌓아서 넣을 수 있음
@Accessors(chain = true)        // setter 메서드를 쌓아서 사용 가능
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "roleList")      // user테이블에서 설정해서 따로 하지 않아도 됨
    private List<User> userList;
}
