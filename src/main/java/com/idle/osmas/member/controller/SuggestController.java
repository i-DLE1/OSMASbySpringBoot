package com.idle.osmas.member.controller;

import com.idle.osmas.member.dto.ReviewsDTO;
import com.idle.osmas.member.dto.SuggestsDTO;
import com.idle.osmas.member.paging.Pagenation;
import com.idle.osmas.member.paging.SelectCriteria;
import com.idle.osmas.member.service.MemberServiceImpl;
import com.idle.osmas.member.service.SuggestServiceImpl;
import com.idle.osmas.seller.dto.CategoryDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        // 카테고리 이름 가져옴
        List<CategoryDTO> categoryList = suggestService.selectCategory();
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
        m.addAttribute("categoryList",categoryList);
    }

    @PostMapping("/suggest/write")
    @ResponseBody
    public String suggestWrite(  @RequestParam("name") String name,
                                 @RequestParam("title") String title,
                                 @RequestParam("content") String content,Principal principal){
        String id = principal.getName();
        int resultWrite =  suggestService.suggestWrite(name,title,content,id);
        if(resultWrite >0){
            return "글 작성 성공";
        }else{
            return "글작성 실패";
        }
    }

    @PostMapping("/suggest/modify")
    @ResponseBody
    public String suggestModify(@RequestParam("no") int no,
                                @RequestParam("nickname") String nickname,
                                @RequestParam("title") String title,
                                @RequestParam("content") String content,Principal principal){
        String result = "글 수정에 성공하셨습니다";
        String id = principal.getName();
        String userNickname = memberService.selectNicknameById(id);
        if(!nickname.equals(userNickname)){
            return "글쓴이와 계정이 다릅니다";
        }
        SuggestsDTO suggest = new SuggestsDTO();
        suggest.setNo(no);
        suggest.setTitle(title);
        suggest.setContent(content);
        int modifyResult = suggestService.modifySuggest(suggest);
        return result;
    }



    @PostMapping("/suggest/remove")
    @ResponseBody
    public String suggestRemove(@RequestParam("no") int no,
                                @RequestParam("nickname") String nickname,Principal principal){
        String result = "글 삭제에 성공하셨습니다";
        String id = principal.getName();
        String userNickname = memberService.selectNicknameById(id);
        if(!nickname.equals(userNickname)){
            return "글쓴이와 계정이 다릅니다";
        }
        int removeResult = suggestService.removeSuggest(no);
        return result;
    }
    @PostMapping("/suggest/suggest")
    @ResponseBody
    public String suggestContent(@RequestParam("no")int no){
        String content = suggestService.selectContent(no);
        return content;
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
        // 카테고리 이름 가져옴
        List<CategoryDTO> categoryList = suggestService.selectCategory();


        if(searchCondition != null && !"".equals(searchCondition)){
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
        }else{
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
        }
        List<SuggestsDTO> suggestList = suggestService.selectSuggestList(selectCriteria);

        m.addAttribute("suggestList", suggestList);
        m.addAttribute("selectCriteria", selectCriteria);
        m.addAttribute("categoryList",categoryList);
    }
}
