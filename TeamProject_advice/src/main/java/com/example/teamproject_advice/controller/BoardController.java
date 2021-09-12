package com.example.teamproject_advice.controller;

import com.example.teamproject_advice.model.algorithm.Logic;
import com.example.teamproject_advice.model.entity.Board;
import com.example.teamproject_advice.model.entity.Comment;
import com.example.teamproject_advice.service.implement.BoardService;
import com.example.teamproject_advice.service.implement.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller                         // controller임을 명시(spring boot), 학원 : 홈페이지 > 공부 참고 참조
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;

    // /board/list로 연결됨
    // parameter로는 search, Model, Pageable( page, size, sort )가 있음
    @GetMapping("/list")
    public String list(@RequestParam(value = "search", required = false) String search,
                       Model model, Pageable pageable) {

        // 검색한 내용이 없을 때 (search param이 없을 때)
        Page<Board> boardPage = ( search == null || search.isEmpty() )? boardService.boardListPage(pageable) : boardService.searchBoardPage(search, boardService.checkPageable(pageable));

        // model(return) 에 값을 전송
        model.addAttribute("list", boardPage);                          // list(key), boardPage(value, Page type)
        model.addAttribute("paging", Logic.paging(pageable, boardPage.getTotalPages()));         // paging(key), 페이지 번호를 위한 list(value, List type)
        model.addAttribute("page", boardService.checkPageable(pageable));    // page(key), 현재 페이지 정보 detail에 전달용(value, Pageable type)
        model.addAttribute("lastPageNumber", boardService.boardListLastPage(pageable.getPageSize(), search));   // lastPageNumber(key), 마지막 페이지 확인용(value, int type)

        model.addAttribute("search", search);       // search(key), value String type

        return "board/list";        // board/list.html로 연결
    }

    // /board/detail로 연결됨
    // parameter로는 id, size, Model이 있음
    @GetMapping("/detail")
    public String detail(@RequestParam("id") Long id,
                         @RequestParam("size") int size,
                         Model model) {

        Board board = boardService.findById(id);                 // id로 검색해서 테이블의 행 가져오기
        model.addAttribute(board);     // board(key), board(value, Board type)와 동일

        // model(return) 에 값을 전송

        model.addAttribute("dateFotmat", DateTimeFormatter.ofPattern("yyyy MM dd"));
        model.addAttribute("boardNext", boardService.findById(id+1));        // boardNext(key), 다음 글(value, board type)
        model.addAttribute("boardPrev", boardService.findById(id-1));        // boardPrev(key), 이전 글(value, board type)

        // 목록으로
        model.addAttribute("page", boardService.returnPageNumber(id, size)); // page(key), 목록 번호(value, int type)
        model.addAttribute(size);                               // size(key), page 설정 size(value, int type)


        // 댓글 테스트용

        List<Comment> commentList = board.getCommentList();
        model.addAttribute("commentList", commentList);

        Map<String, String> testMap = new HashMap<>();

        testMap.put("registeredAt", "2021-09-01 12:00");
        testMap.put("comment", "테스트 내용입니다 테스트 내용입니다 테스트 내용입니다 테스트 내용입니다 테스트 내용입니다 테스트 내용입니다 테스트 내용입니다 테스트 내용입니다 테스트 내용입니다 테스트 내용입니다 테스트 내용입니다 테스트 내용입니다 테스트 내용입니다 테스트 내용입니다 테스트 내용입니다 테스트 내용입니다 테스트 내용입니다 ");
        testMap.put("nickname", "멍청한비둘기들");

        model.addAttribute("comment", testMap);     // comment(key), testMap(value, May<String, String> type)

        return "board/detail";
    }


    // 글쓰기 창
    @PostMapping("/write")
    public String beforeWrite(@RequestParam(value = "boardId", required = false) Long id,
                              Model model) {
        model.addAttribute("boardId", id);
        Board board = boardService.findById(id);

        model.addAttribute("titleText", board.getTitle());
        model.addAttribute("contentText", board.getContent());
        return "/board/write";
    }

    @PostMapping("/writeAction.do")
    public String write(@RequestParam(value = "id", required = false) Long id,
                        @RequestParam(value = "title") String title,
                        @RequestParam(value = "comment") String comment,
                        Model model) {

        boardService.boardWrite(5L, id, title, comment);

//        String returnView = "/board/detail(id=" + id + ")";
        return "redirect:/board/list";
    }


    // 게시글 삭제
    @PostMapping("/detail/delete.do")
    public String boardDelete(@RequestParam(value = "id", required = false) Long id) {
        switch ( boardService.BoardDelete(id) ) {
            case "success":
                System.out.println("delete.do : " + id + "번 게시글 삭제 성공");
                break;
            case "fail":
                System.out.println("delete.do : " + id + "번 게시글이 없습니다");
            case "error":
                System.out.println("delete.do : " + id + "번 게시글 삭제 중 에러");
                break;
            default:
                System.out.println(boardService.BoardDelete(id));
        }
        return "redirect:/board/list";
    }


    // 댓글 생성
    @PostMapping("/commentWriteAction.do")
    public String commentWriteAction (@RequestParam(value = "commentId", required = false) Long commentId,
                                      Long boardId, String comment) {
        commentService.commentWrite(boardId, commentId, comment);
        return "redirect:/board/detail?id=" + boardId + "&size=" + 10;
    }

    @PostMapping("/commentDelete.do")
    public String commentDelete (Long commentId) {

        return "redirect:/board/detail";
    }

}