package com.example.teamproject_advice.service;

import com.example.teamproject_advice.TeamProjectAdviceApplicationTests;
import com.example.teamproject_advice.service.implement.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BoardServiceTest extends TeamProjectAdviceApplicationTests {

    @Autowired
    private BoardService service;

//    @Test
//    public void pagingTest() {
//        for (Integer i : service.paging(5)) {
//            System.out.println(i);
//        }
//    }
}
