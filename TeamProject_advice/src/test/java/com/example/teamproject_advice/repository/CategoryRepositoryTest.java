package com.example.teamproject_advice.repository;

import com.example.teamproject_advice.TeamProjectAdviceApplicationTests;
import com.example.teamproject_advice.model.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class CategoryRepositoryTest extends TeamProjectAdviceApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test //생성
    public void create() {
        Category ca1 = new Category();

        ca1.setId(1L);
        ca1.setType("Jean");
        ca1.setTitle("Pants");

        ca1.setCreatedAt(LocalDateTime.now());
        ca1.setCreatedBy("admin01");

//        ca1.setUpdated_at(LocalDateTime.now());
//        ca1.setUpdated_by("");

        Category newCategory = categoryRepository.save(ca1);
        System.out.println(newCategory);
    }

    @Test //읽기
    public void read() {

        List<Category> categoriesList = categoryRepository.findAll();

        for (Category category : categoriesList) {

            System.out.println(category.getId());
            System.out.println(category.getType());
            System.out.println(category.getTitle());
        }
    }

//    @Test //수정
//    public void update() {
//        Optional<Category> category = categoryRepository.findById(1L);
//
//        category.ifPresent(c -> {
//            c.setType("Jean");
//            c.setTitle("Pants");
////            c.setAccount("jy");
////            c.setPassword("1234");
//            c.setUpdated_at(LocalDateTime.now());
////            c.setUpdatedBy("admin");
//
//            categoryRepository.save(c);
//
//        });
//
//    }
//
//    @Test //삭제
//    public void delete() {
//        Optional<User> user = userRepository.findById(2L);
//
//        user.ifPresent(u -> {
//            userRepository.delete(u);
//        });
//    }
}