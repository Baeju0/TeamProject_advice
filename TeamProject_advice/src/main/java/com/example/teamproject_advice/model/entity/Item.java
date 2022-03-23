package com.example.teamproject_advice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Accessors(chain = true)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private String name;
    private String title;
    private String content;
    private Integer price;
    private String brandName;

    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;

    private LocalDateTime createdAt;
    private String createdBy;

    private LocalDateTime updatedAt;
    private String updatedBy;

    private Long partnerId;

}
