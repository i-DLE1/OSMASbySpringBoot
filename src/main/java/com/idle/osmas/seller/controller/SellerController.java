package com.idle.osmas.seller.controller;

import com.idle.osmas.seller.dto.ProjectDTO;
import com.idle.osmas.seller.dto.ProjectQnAAnswerDTO;
import com.idle.osmas.seller.dto.ProjectQnADTO;
import com.idle.osmas.seller.service.SellerPageServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/seller/")
public class SellerController {

    private final SellerPageServiceImpl sellerPageService;

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

    @GetMapping("/projectList")
    public String getProjectList(@RequestParam(required = false) Optional<String> listType,
                                 @RequestParam(required = false) String search,
                                 Model model){

        Map<String, Object> searchCriteria = new HashMap<>();
        searchCriteria.put("listType", listType.get().toString().toUpperCase());
        searchCriteria.put("search", search);
        searchCriteria.put("userId", "admin01");

        List<ProjectDTO> projectList = sellerPageService.selectByProgressAndSearchProjectManagement(searchCriteria);

        String defaultSearch = search == null ? "검색할 프로젝트 명을 입력하세요" : search;
        model.addAttribute("listType", listType(listType));
        model.addAttribute("userName","seller01"); // 사용자명
        model.addAttribute("search",defaultSearch);
        model.addAttribute("projectList",projectList);

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
                                    Model model){


        Map<String, Object> searchCriteria = new HashMap<>();

        searchCriteria.put("listType",listType.get().toString());
        searchCriteria.put("searchType",searchType);
        searchCriteria.put("search", search);
        searchCriteria.put("userId", "admin01");

        List<ProjectQnADTO> projectQnAList = sellerPageService.selectByListTypeAndSearchProjectQnA(searchCriteria);

        String defaultSearch = search == null ? "검색할 프로젝트 명을 입력하세요" : search;
        model.addAttribute("listType", listType(listType));
        model.addAttribute("userName","admin01"); // 사용자명
        model.addAttribute("search",search);
        model.addAttribute("searchType",searchType);
        model.addAttribute("projectQnAList", projectQnAList);
        return "/seller/sellerqa";
    }

}
