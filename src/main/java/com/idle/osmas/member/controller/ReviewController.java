package com.idle.osmas.member.controller;

//import com.idle.osmas.member.service.ReviewServiceImpl;
import com.idle.osmas.member.dto.ReviewsDTO;
import com.idle.osmas.member.paging.Pagenation;
import com.idle.osmas.member.paging.SelectCriteria;
import com.idle.osmas.member.service.ReviewServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class ReviewController {
    private final ReviewServiceImpl reviewService;

    public ReviewController(ReviewServiceImpl reviewService){
        this.reviewService = reviewService;
    }
    @GetMapping("/review/review")
    public void goReview(@RequestParam(required = false) String searchCondition,
                         @RequestParam(required = false) String searchValue,
                         @RequestParam(value="currentPage", defaultValue = "1") int pageNo){

//        Map<String, String> searchMap = new HashMap<>();
//        searchMap.put("searchCondition" , searchCondition);
//        searchMap.put("searchValue" ,searchValue);
//        int totalCount = reviewService.selectTotalCount(searchMap);
//
//        int limit = 5;
//
//        int buttonAmount = 5;
//
//        SelectCriteria selectCriteria = null;
//
//        if(searchCondition != null && !"".equals(searchCondition)){
//            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
//        }else{
//            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
//        }
//        List<ReviewsDTO> ReviewsList = reviewService.selectReviewList(selectCriteria);

    }


}
