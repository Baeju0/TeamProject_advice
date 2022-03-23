package com.example.teamproject_advice.repository;

import com.example.teamproject_advice.TeamProjectAdviceApplicationTests;
import com.example.teamproject_advice.model.entity.Board;
import com.example.teamproject_advice.model.entity.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ItemRepositoryTest extends TeamProjectAdviceApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test //생성
    public void create() {
        for (int i=1; i<100; i++) {
            Item item = Item.builder()
                    .status("test")
                    .name("testUser" + i)
                    .title(i + "번째 게시글")
                    .content(i + "번 즐겁다")
                    .price(10000)
                    .brandName("Kim")
                    .registeredAt(LocalDateTime.now())
                    .createdAt(LocalDateTime.now())
                    .createdBy("adminTest")
                    .partnerId(1L)
                    .build();

            itemRepository.save(item);
            System.out.println("생성 완료 : " + item.getName());
        }
    }

//    @Test //읽기
//    public void read() {
//
//        List<Item> itemList = itemRepository.findAll();
//
//        for (Item item : itemList) {
//
//            System.out.println(item.getId());
//            System.out.println(item.getStatus());
//            System.out.println(item.getName());
//            System.out.println(item.getTitle());
//            System.out.println(item.getContent());
//            System.out.println(item.getPrice());
//            System.out.println(item.getBrand_name());
//            System.out.println(item.getBrand_name());
//        }
//    }

//    @Test //수정
//    public void update() {
//        Optional<User> user = userRepository.findById(2L);
//
//        user.ifPresent(u -> {
//            u.setAccount("jy");
//            u.setPassword("1234");
//            u.setUpdatedAt(LocalDateTime.now());
//            u.setUpdatedBy("admin");
//
//            userRepository.save(u);
//
//        });
//
//    }
//
    @Test //삭제
    public void delete() {
        List<Item> item = itemRepository.findAll();

        item.stream().forEach(u -> {
            System.out.println(u.getId() + " 삭제 완료");
            itemRepository.delete(u);
        });
    }
}