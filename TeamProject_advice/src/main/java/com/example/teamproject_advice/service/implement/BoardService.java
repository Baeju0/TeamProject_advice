package com.example.teamproject_advice.service.implement;

import com.example.teamproject_advice.model.entity.Board;
import com.example.teamproject_advice.model.entity.User;
import com.example.teamproject_advice.repository.BoardRepository;
import com.example.teamproject_advice.repository.UserRepository;
import com.example.teamproject_advice.service.interfaces.BoardServiceInterface;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor            // Repository와 Entity 연결
@Service                            // service임을 명시 (JPA에게 알려줌)
@Builder
public class BoardService implements BoardServiceInterface {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    // List : 두루마리, Page : 책. List는 한 페이지로 된 문서라면, Page는 그 문서에 순서를 매겨 책처럼 만든 것이다
    // pageable.getPageSize : 몇 개의 객체를 기준으로 한 페이지를 구성했는지
    // pageable.getPageNumber : 현재 몇 페이지인지. index와 마찬가지로 0페이지부터 시작한다

    // 현재 페이지 출력
    @Override
    public Page<Board> boardListPage(Pageable pageable) {
        int lastPage = boardListLastPage(pageable.getPageSize(), null);
        int valPage = Math.max(pageable.getPageNumber(), 0);

        // 삼항 연산자, 항이 3개인 연산자. (조건)? 참일 때 : 거짓일 때;
//        if (true) {
//            System.out.println(true);
//        }
//        else {
//            System.out.println(false);
//        }
        // 위와 같다
//        ( true )? System.out.println(true) : System.out.println(false);
        Pageable setPageable = (valPage > lastPage)? checkPageable( PageRequest.of(lastPage, pageable.getPageSize(), pageable.getSort()) ) : checkPageable( pageable );

        return boardRepository.findAll(setPageable);
    }

    // 게시글 검색
    @Override
    public Page<Board> searchBoardPage(String search, Pageable pageable) {
        return boardRepository.findByTitleContaining(search, pageable);
    }

    // 게시글 생성, 수정
    @Override
    public void boardWrite(String account, Board board) {
        User user = userRepository.findByAccount(account);
        Board setBoard = ( board.getId() == null )? new Board() : findById(board.getId());
        setBoard.setUser(user);

        // 게시글 생성
        if ( setBoard.getId() == null ) {
            setBoard
                    .setCreatedAt(LocalDateTime.now())
                    .setCreatedBy("user")
                    .setRegisteredAt(LocalDateTime.now())
                    .setViewCount(0L);
        }
        // 게시글 수정
        else {
            setBoard.setUpdatedAt(LocalDateTime.now())
                    .setUpdatedBy("user");
        }

        setBoard.setTitle(board.getTitle())
                .setContent(board.getContent());

        boardRepository.save(setBoard);
    }

    // ========== 게시글 삭제 ==========
    @Override
    public String BoardDelete(Long id) {
        try {
            Board board = findById(id);
            boardRepository.delete(board);
            if ( board == null ) { return "fail"; }
            return "success";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }


    // url에 pageable이 입력되어 있는지 확인하는 메서드.
    @Override
    public Pageable checkPageable(Pageable pageable) {
        // PageRequest.of(현재 페이지, 페이지 기준, 정렬 기준) : Pageable를 생성할 때 사용한다.
        return (pageable.getSort() != Sort.unsorted())? pageable : PageRequest.of(0, 10, Sort.Direction.DESC, "id");
    }

    // 모든 행의 마지막 페이지를 출력하는 메서드, 페이지 기준인 size를 입력해야 한다.
    @Override
    public int boardListLastPage(int size, String search) {
        Pageable pageable = PageRequest.of(0, size, Sort.Direction.DESC, "id");
        Page<Board> page = ( search == null )? boardRepository.findAll(pageable) : boardRepository.findByTitleContaining(search, pageable);
        int lastPage = page.getTotalPages();
        return Math.max(0, lastPage -1);            // index와 맞추기 위해
    }

    // id로 행을 검색하는 메서드, .orElse(null) : 값이 없을 때 null을 반환한다.
    @Override
    public Board findById(Long id) {
        return boardRepository.findById(id).orElse(null);
    }

    // 페이징을 하기 위한 메서드, 페이지 정보를 넣으면 기준으로 5개의 페이지 번호를 만들어준다.
    @Override
    public List<Integer> paging(Pageable pageable, int totalPage) {
        int page = pageable.getPageNumber();

        int d = (0<page)? page/5 : 0;       // 시작 번호 지정 (최대 5개)

        int startInt = d*5;
        int endInt = ( (d*5 +4) > totalPage -1)? totalPage -1 : d*5 +4;     // 계산식이 마지막 페이지보다 크면

        List<Integer> list = new ArrayList<>();

        // 전체 목록에 있는지 검사
        for (; startInt <= endInt; startInt++) {
            if ( !boardRepository.findAll( PageRequest.of(startInt, pageable.getPageSize(), pageable.getSort()) ).isEmpty() )
            {
                list.add(startInt);
            }
        }

        return list;
    }

    // 현재 몇페이지인지 계산하는 메서드, detail >> search로 넘어가려고 정보를 저장하기 위해 만들었다.
    @Override
    public int returnPageNumber(Long id, int size) {
        List<Board> descFindAll = boardRepository.findAll();
        Collections.reverse(descFindAll);

        return Math.toIntExact(descFindAll.indexOf(id) / size);
    }





}
