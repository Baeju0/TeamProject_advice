package com.example.teamproject_advice.repository;

import com.example.teamproject_advice.model.entity.OrderBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderBoxRepository extends JpaRepository<OrderBox, Long> {
}
