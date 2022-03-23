package com.example.teamproject_advice.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
@ToString(exclude = {"user", "board"})
@Accessors(chain = true)
public class Comment {

    @Id         // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // auto_increment
    private Long id;

    private String comment;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;
    private String updatedBy;

    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;

    @ManyToOne  // N : 1
    private Board board;
    @ManyToOne  // N : 1
    private User user;

}
