package com.example.teamproject_advice.model.algorithm;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class Logic {
    public static List<Integer> paging(Pageable pageable, int totalPage) {
        int page = pageable.getPageNumber();
        int d = (0<page)? page/5 : 0;       // 시작 번호 지정 (최대 5개)

        int startInt = d*5;
        int endInt = Math.min((d * 5 + 4), totalPage - 1);     // paging 개수, 마지막 페이지

        List<Integer> list = new ArrayList<>();

        // 길이만큼 출력
        for (; startInt <= endInt; startInt++) {
                list.add(startInt);
        }

        return list;
    }
}
