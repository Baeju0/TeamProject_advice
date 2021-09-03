package com.example.teamproject_advice.controller;

import com.example.teamproject_advice.model.entity.Board;
import com.example.teamproject_advice.service.implement.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService service;

    @GetMapping("/list")
    public String list(Model model, Pageable pageable) {
//    public String list(@RequestParam("page") int page,
//                       @RequestParam("size") int size,
//                       @RequestParam("sort") String id,
//                       Model model) {
//        Page<Board> boardList = service.boardList(PageRequest.of(page, size, Sort.Direction.DESC, id));
        Page<Board> boardList = service.boardList(pageable);
        model.addAttribute("list", boardList);
        model.addAttribute("page", service.paging(pageable.getPageNumber()));
        return "board/list";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("id") Long id, Model model) {
        Board board = service.findById(id);
        model.addAttribute("detail", board);
        return "board/detail";
    }
}
