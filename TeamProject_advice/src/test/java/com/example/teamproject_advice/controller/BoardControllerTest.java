package com.example.teamproject_advice.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.teamproject_advice.TeamProjectAdviceApplicationTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest
public class BoardControllerTest {

    @Autowired
    private BoardController boardController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(boardController).build();
    }

    @Test
    public void test() throws Exception{

        MultiValueMap<String, String> info = new LinkedMultiValueMap<>();
        info.add("page", "2");
        info.add("size", "10");
        info.add("sort", "id");

        mockMvc.perform(get("/board/list").params(info))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(BoardController.class))
                .andExpect(handler().methodName("list"));

    }
}
