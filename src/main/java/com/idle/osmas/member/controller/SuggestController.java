package com.idle.osmas.member.controller;

import com.idle.osmas.member.dto.ReviewsDTO;
import com.idle.osmas.member.dto.SuggestsDTO;
import com.idle.osmas.member.paging.Pagenation;
import com.idle.osmas.member.paging.SelectCriteria;
import com.idle.osmas.member.service.MemberServiceImpl;
import com.idle.osmas.member.service.SuggestServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class SuggestController {

    private final SuggestServiceImpl suggestService;

    private final MemberServiceImpl memberService;

    public SuggestController(SuggestServiceImpl suggestService
    ,MemberServiceImpl memberService){
        this.suggestService = suggestService;
        this.memberService = memberService;
    }
    @GetMapping("/suggest/suggest")
    public void goSuggest(@RequestParam(required = false) String searchCondition,
                          @RequestParam(required = false) String searchValue,
                          @RequestParam(value="currentPage", defaultValue = "1") int pageNo
            , Model m){
        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition" , searchCondition);
        searchMap.put("searchValue" ,searchValue);
        int totalCount = suggestService.selectTotalCount(searchMap);
        System.out.println("===================" + totalCount+"=========================");
        int limit = 10;

        int buttonAmount = 5;
        SelectCriteria selectCriteria = null;

        if(searchCondition != null && !"".equals(searchCondition)){
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
        }else{
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
        }
        List<SuggestsDTO> suggestList = suggestService.selectSuggestList(selectCriteria);
        m.addAttribute("suggestList", suggestList);
        m.addAttribute("selectCriteria", selectCriteria);

    }
    @GetMapping("/suggest/mysuggest")
    public void goReview(Principal principal, @RequestParam(value="currentPage", defaultValue = "1") int pageNo, Model m){
        Map<String, String> searchMap = new HashMap<>();
        String id = principal.getName();
        String searchValue = memberService.selectNicknameById(id);
        String searchCondition = "nickname";
        searchMap.put("searchCondition" , searchCondition);
        searchMap.put("searchValue" ,searchValue);
        int totalCount = suggestService.selectTotalCount(searchMap);

        int limit = 10;

        int buttonAmount = 5;

        SelectCriteria selectCriteria = null;

        if(searchCondition != null && !"".equals(searchCondition)){
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
        }else{
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
        }
        List<SuggestsDTO> suggestList = suggestService.selectSuggestList(selectCriteria);

        m.addAttribute("suggestList", suggestList);
        m.addAttribute("selectCriteria", selectCriteria);
    }
}
