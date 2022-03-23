package com.example.teamproject_advice.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@ToString
@Builder
@Accessors(chain = true)
public class OrderBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private String revAddress;
    private String revName;
    private String paymentType;

    private BigDecimal totalPrice;
    private Integer totalQuantity;
    private LocalDateTime orderAt;
    private LocalDateTime arrivalDate;

    @CreatedDate
    private LocalDateTime createdAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @LastModifiedBy
    private String updatedBy;
}
