package com.example.teamproject_advice.repository;

import com.example.teamproject_advice.TeamProjectAdviceApplicationTests;
import com.example.teamproject_advice.model.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoleRepositoryTest extends TeamProjectAdviceApplicationTests {
    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void read() {
        List<Role> roleList = roleRepository.findAll();

        for ( Role role : roleList ) {
            System.out.println(role);
        }
    }
}
