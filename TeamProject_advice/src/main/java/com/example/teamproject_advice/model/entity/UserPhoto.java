package com.example.teamproject_advice.model.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString
@Builder
@Accessors(chain = true)
public class UserPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileDir;
    private LocalDateTime createdAt;
    private String createdBy;


    private Long userId;

}
