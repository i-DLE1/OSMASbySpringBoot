package com.idle.osmas.member.controller;

//import com.idle.osmas.member.service.ReviewServiceImpl;
import com.idle.osmas.member.dto.ReviewDTO;
import com.idle.osmas.member.dto.ReviewsDTO;
import com.idle.osmas.member.dto.SponsoredsDTO;
import com.idle.osmas.member.paging.Pagenation;
import com.idle.osmas.member.paging.SelectCriteria;
import com.idle.osmas.member.service.MemberServiceImpl;
import com.idle.osmas.member.service.ReviewServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
        System.out.println(reviewList.get(0));

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

    @PostMapping("/review/review")
    @ResponseBody
    public String reviewContent(@RequestParam("no") int no){
        String content = reviewService.selectContent(no);
        return content;
    }

    @PostMapping("/review/write")
    @ResponseBody
    public String writeReview(@RequestParam("no") int SponsoredNo,
                              @RequestParam("nickname") String nickname,
                              @RequestParam("title") String title,
                              @RequestParam("content") String content) throws Exception {
        System.out.println("===============");
        System.out.println(SponsoredNo);
        System.out.println(nickname);
        System.out.println(title);
        System.out.println(content);
        String result = "리뷰 작성에 성공했습니다";
        int memberNo = memberService.selectNoByNickname(nickname);
        int refDeliveryStatusCode = reviewService.selectDeliveryStatusCodeistNoBySponsoredNo(SponsoredNo);
        System.out.println(memberNo);
        System.out.println(refDeliveryStatusCode);
        ReviewDTO review = new ReviewDTO();
        review.setContent(content);
        review.setTitle(title);
        review.setRefDeliveryStatusCode(refDeliveryStatusCode);
        review.setRefMemberNo(memberNo);

        int writeResult = reviewService.writeReview(review);

        return result;
    }
    @PostMapping("/review/modify")
    @ResponseBody
    public String modifyReview(HttpServletRequest request,Principal principal) throws Exception {
        String result = "글 수정에 성공했습니다";
        String id = principal.getName();
        String nickname =request.getParameter("nickname");
        String userNickname = memberService.selectNicknameById(id);
        if(!nickname.equals(userNickname)){
            return "글쓴이와 계정이 다릅니다";
        }

        String title = request.getParameter("title");
        String content = request.getParameter("content");
        int no = Integer.parseInt(request.getParameter("no"));

        ReviewsDTO review = new ReviewsDTO();
        review.setContent(content);
        review.setTitle(title);
        review.setNickname(nickname);
        review.setRefDeliveryStatusCode(no);
        int modifyResult = reviewService.modifyReview(review);
        if(modifyResult<1){
            result = "글 수정에 실패하셨습니다";
        }
        return result;
    }

    @PostMapping("/review/remove")
    @ResponseBody
    public String removeReview(HttpServletRequest request,Principal principal){
        String result = "글 삭제에 성공했습니다";

        String id = principal.getName();
        String userNickname = memberService.selectNicknameById(id);
        String nickname =request.getParameter("nickname");
        int no = Integer.parseInt(request.getParameter("no"));
        if(!nickname.equals(userNickname)){
            return "글 삭제에 권한이 없습니다";
        }

        int removeResult = reviewService.removeReview(no);
        if(removeResult<1){
            result = "글 삭제에 실패하셨습니다";
        }
        return result;
    }
}
