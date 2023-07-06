package com.idle.osmas.seller.controller;

import com.idle.osmas.member.dto.UserImpl;
import com.idle.osmas.seller.dto.MemberDTO;
import com.idle.osmas.seller.dto.ProjectDTO;
import com.idle.osmas.seller.dto.ProjectQnAAnswerDTO;
import com.idle.osmas.seller.dto.ProjectQnADTO;
import com.idle.osmas.seller.service.SellerPageServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/seller")
public class SellerController {

    private final SellerPageServiceImpl sellerPageService;

    private int DEFAULT_MAX_ROWS = 10;

    public SellerController(SellerPageServiceImpl sellerPageService) {
        this.sellerPageService = sellerPageService;
    }

    public String listType(Optional<String> listType){
        String resultListType = "";
        switch (listType.orElse("")){
            case "all" :
                resultListType = "전체조회";
                break;
            case "screening" :
                resultListType = "심사중";
                break;
            case "processing" :
                resultListType = "진행중";
                break;
            case "refuse" :
                resultListType = "반려";
                break;
            case "cancel" :
                resultListType = "취소";
                break;
            case "wait" :
                resultListType = "답변대기중";
                break;
            case "complete" :
                resultListType = "완료";
                break;
            default:
                resultListType = "전체조회";
        }
        return resultListType;
    }
    public Map<String, Integer> getPagenation(int currentPage, int maxPage){

        Map<String, Integer> getPageInfo = new HashMap<>();
        int minPage = 1;
        int startPageNum = 1;
        int endPageNum = maxPage;
        if(maxPage - 2 > currentPage){
            if(currentPage > 2) startPageNum  = currentPage - 2;
            if(endPageNum - 2 > currentPage) endPageNum = currentPage + 2;
        }

        getPageInfo.put("startPageNum",startPageNum);
        getPageInfo.put("endPageNum", endPageNum);
        getPageInfo.put("minPageNum", minPage);
        getPageInfo.put("maxPageNum", maxPage);
        getPageInfo.put("currentPageNum", currentPage);
                return getPageInfo;
//        return model;
    }

    @GetMapping("/projectList")
    public String getProjectList(@RequestParam(required = false) Optional<String> listType,
                                 @RequestParam(required = false) String search,
                                 @RequestParam(required = false) Integer pageNo,
                                 Principal principal, Model model){
        if(listType.isEmpty()){
            return "redirect:/seller/projectList?listType=all";
        }
        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        if(pageNo == null ) pageNo = 1;

        Map<String, Object> searchCriteria = new HashMap<>();

        int startNo = 1 + ((pageNo - 1) * DEFAULT_MAX_ROWS);
        int endNo = ((pageNo) * DEFAULT_MAX_ROWS);

        searchCriteria.put("listType", listType.get().toString().toUpperCase());
        searchCriteria.put("search", search);
        searchCriteria.put("userNo", user.getNo());
        searchCriteria.put("startNo", startNo);
        searchCriteria.put("endNo", endNo);


        int count = sellerPageService.selectByProgressAndSearchProjectManagementCount(searchCriteria);

        int endRow = count - ((pageNo -1) * DEFAULT_MAX_ROWS) < 0 ? count % DEFAULT_MAX_ROWS : count -((pageNo - 1) * DEFAULT_MAX_ROWS);

        List<ProjectDTO> projectList = sellerPageService.selectByProgressAndSearchProjectManagement(searchCriteria);

        int maxPage = ((int) Math.floor((double) count / (double) DEFAULT_MAX_ROWS)) + 1;

        Map<String, Integer> pageInfo = getPagenation(pageNo,maxPage);

        model.mergeAttributes(pageInfo);

        String defaultSearch = search == null ? "검색할 프로젝트 명을 입력하세요" : search;

        model.addAttribute("endRow", endRow);
        model.addAttribute("search", defaultSearch);
        model.addAttribute("listType", listType(listType));
        model.addAttribute("userName", user.getName()); // 사용자명
        model.addAttribute("projectList", projectList);

        return "/seller/sellerProjectList";
    }



    @GetMapping(value = {"/","/orderList"})
    public String getOrderList(@RequestParam(required = false) String listType,
                               @RequestParam(required = false) String searchType,
                               @RequestParam(required = false) String search){
        System.out.println("listType = " + listType);
        System.out.println("searchType = " + searchType);
        System.out.println("search = " + search);
        return "/seller/sellerOrderList";
    }

    @GetMapping("/projectQnAList")
    public String getProjectQnAList(@RequestParam(required = false) Optional<String> listType,
                                    @RequestParam(required = false) String searchType,
                                    @RequestParam(required = false) String search,
                                    @RequestParam(required = false) Integer pageNo,
                                    Principal principal, Model model){

        if(listType.isEmpty()){
            return "redirect:/seller/projectQnAList?listType=all";
        }

        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        String defaultSearch = search == null ? "검색할 키워드를 입력하세요" : search;
        if(pageNo == null ) pageNo = 1;

        int startNo = 1 + ((pageNo - 1) * DEFAULT_MAX_ROWS);
        int endNo = ((pageNo) * DEFAULT_MAX_ROWS);

        Map<String, Object> searchCriteria = new HashMap<>();
        searchCriteria.put("listType",listType.get().toString());
        searchCriteria.put("searchType",searchType);
        searchCriteria.put("search", search);
        searchCriteria.put("userNo", user.getName());
        searchCriteria.put("startNo", startNo);
        searchCriteria.put("endNo", endNo);


        int count = sellerPageService.selectByListTypeAndSearchProjectQnACount(searchCriteria);

        int endRow = count - ((pageNo -1) * DEFAULT_MAX_ROWS) < 0 ? count % DEFAULT_MAX_ROWS : count - ((pageNo - 1) * DEFAULT_MAX_ROWS);

        List<ProjectQnADTO> projectQnAList = sellerPageService.selectByListTypeAndSearchProjectQnA(searchCriteria);

        model.addAttribute("listType", listType(listType));
        model.addAttribute("userName",user.getName()); // 사용자명
        model.addAttribute("search",defaultSearch);
        model.addAttribute("searchType",searchType);
        model.addAttribute("projectQnAList", projectQnAList);
        model.addAttribute("endRow", endRow);

        int maxPage = ((int) Math.floor((double) count / (double) DEFAULT_MAX_ROWS)) + 1;

        Map<String, Integer> pageInfo = getPagenation(pageNo,maxPage);

        model.mergeAttributes(pageInfo);


        return "/seller/sellerqa";
    }

}
