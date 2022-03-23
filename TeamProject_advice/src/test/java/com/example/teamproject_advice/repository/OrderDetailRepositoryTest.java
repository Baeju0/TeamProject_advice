package com.example.teamproject_advice.repository;

import com.example.teamproject_advice.TeamProjectAdviceApplicationTests;
import com.example.teamproject_advice.model.entity.OrderDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderDetailRepositoryTest extends TeamProjectAdviceApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void read() {
        List<OrderDetail> orderDetailList = orderDetailRepository.findAll();

        for (OrderDetail detail : orderDetailList) {
            detail.toString();
        }
    }
}
