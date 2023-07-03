package com.idle.osmas.member.controller;

//import com.idle.osmas.member.service.ReviewServiceImpl;
import com.idle.osmas.member.dto.ReviewsDTO;
import com.idle.osmas.member.dto.SponsoredsDTO;
import com.idle.osmas.member.paging.Pagenation;
import com.idle.osmas.member.paging.SelectCriteria;
import com.idle.osmas.member.service.MemberServiceImpl;
import com.idle.osmas.member.service.ReviewServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class ReviewController {
    private final ReviewServiceImpl reviewService;
    private final MemberServiceImpl memberService;

    public ReviewController(ReviewServiceImpl reviewService,MemberServiceImpl memberService){

        this.reviewService = reviewService;
        this.memberService = memberService;
    }
    @GetMapping("/review/review")
    public void goReview(@RequestParam(required = false) String searchCondition,
                                 @RequestParam(required = false) String searchValue,
                                 @RequestParam(value="currentPage", defaultValue = "1") int pageNo
                        , Model m){

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition" , searchCondition);
        searchMap.put("searchValue" ,searchValue);
        int totalCount = reviewService.selectTotalCount(searchMap);

        int limit = 10;

        int buttonAmount = 5;

        SelectCriteria selectCriteria = null;

        if(searchCondition != null && !"".equals(searchCondition)){
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
        }else{
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
        }
        List<ReviewsDTO> reviewList = reviewService.selectReviewList(selectCriteria);

        m.addAttribute("reviewList", reviewList);
        m.addAttribute("selectCriteria", selectCriteria);
    }

    @GetMapping("/review/myReview")
    public void goReview(Principal principal, @RequestParam(value="currentPage", defaultValue = "1") int pageNo, Model m){
        Map<String, String> searchMap = new HashMap<>();
        String id = principal.getName();
        String searchValue = memberService.selectNicknameById(id);
        String searchCondition = "nickname";
        searchMap.put("searchCondition" , searchCondition);
        searchMap.put("searchValue" ,searchValue);
        int totalCount = reviewService.selectTotalCount(searchMap);

        int limit = 10;

        int buttonAmount = 5;

        SelectCriteria selectCriteria = null;

        if(searchCondition != null && !"".equals(searchCondition)){
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
        }else{
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
        }
        List<ReviewsDTO> reviewList = reviewService.selectReviewList(selectCriteria);

        m.addAttribute("reviewList", reviewList);
        m.addAttribute("selectCriteria", selectCriteria);
    }
    @GetMapping("/review/reviewSelect")
    public void goReview3(Principal principal,@RequestParam(value="currentPage", defaultValue = "1") int pageNo, Model m){
        String id = principal.getName();
        int totalCount = reviewService.selectTotalCountSponsored(id);
        int limit = 10;
        int buttonAmount = 5;
        SelectCriteria selectCriteria = null;
        selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, "id", id);

        List<SponsoredsDTO> sponsoredList = reviewService.selectSponsoredList(selectCriteria);
        System.out.println(sponsoredList);
        m.addAttribute("sponsoredList", sponsoredList);
        m.addAttribute("selectCriteria",selectCriteria);

    }

    @GetMapping("/review/reviewWrite")
    public void goReview4(){}
}
