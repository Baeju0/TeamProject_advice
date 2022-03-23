package com.example.teamproject_advice.repository;

import com.example.teamproject_advice.TeamProjectAdviceApplicationTests;
import com.example.teamproject_advice.model.entity.OrderBox;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class OrderBoxRepositoryTest extends TeamProjectAdviceApplicationTests {

    @Autowired
    private OrderBoxRepository orderBoxRepository;

    @Test
    public void read() {
        List<OrderBox> orderBoxList = orderBoxRepository.findAll();

        for (OrderBox box : orderBoxList) {
            box.toString();
        }
    }
}
