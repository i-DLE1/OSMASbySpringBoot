package com.idle.osmas.member.paging;

public class Pagenation {

    public static SelectCriteria getSelectCriteria(int pageNo,int totalCount, int limit,int buttonAmount){
        return getSelectCriteria(pageNo, totalCount, limit, buttonAmount, null, null);
    }

    public static SelectCriteria getSelectCriteria(int pageNo,int totalCount, int limit,int buttonAmount,String searchCondition,String searchValue){
        int maxPage;
        int startPage;
        int endPage;
        int startRow;
        int endRow;

        maxPage = (int)((double)totalCount/limit +0.9);
        startPage = (int)(Math.ceil((double) pageNo/ buttonAmount) -1) *buttonAmount +1; // 버튼 첫번째
        endPage = startPage + buttonAmount -1; // 버튼 마지막

        if(maxPage < endPage){ // 마지막 버튼이 maxpage보다 많으면
            endPage = maxPage;
        }

        if(maxPage == 0 && endPage ==0){
            maxPage = startPage;
            endPage = startPage;
        }
        startRow  = (pageNo - 1) * limit + 1;
        endRow = startRow + limit -1;

        SelectCriteria selectCriteria = new SelectCriteria(pageNo, totalCount, limit, buttonAmount, maxPage, startPage, endPage,startRow,endRow,searchCondition, searchValue);

        return selectCriteria;
    }
}
