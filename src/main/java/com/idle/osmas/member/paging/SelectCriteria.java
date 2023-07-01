package com.idle.osmas.member.paging;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SelectCriteria {
    private int pageNo; // 페이지 번호
    private int totalCount; // 전체 게시물 수
    private int limit;  // 한 페이지에 보여줄 게시물 수
    private int buttonAmount; // 페이징 버튼 갯수
    private int maxPage; // 가장 마지막 페이지
    private int startPage; // 페이징 버튼의 시작하는 페이지
    private int endPage; // 마지막 페이지
    private int startRow; // DB조회 시 최신 글 부터 조회해야 하는 행의 시작
    private int endRow; // DB 조회시 최신 글부터 조회해야 하는 행의 마지막 수
    private String searchCondition; // 검색 조건
    private String searchValue; // 검색어
}
