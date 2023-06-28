package com.idle.osmas.seller.controller;

import com.idle.osmas.seller.dto.ProjectDTO;
import com.idle.osmas.seller.service.ProejctServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/seller/")
public class SellerController {

    private final ProejctServiceImpl proejctService;

    public SellerController(ProejctServiceImpl proejctService) {
        this.proejctService = proejctService;
    }

    @GetMapping("/projectList")
    public String getProjectList(@RequestParam(required = false) Optional<String> listType,
                                 @RequestParam(required = false) String searchType,
                                 @RequestParam(required = false) String search,
                                 Model model){

        System.out.println("listType = " + listType);
        System.out.println("searchType = " + searchType);
        System.out.println("search = " + search);
//        System.out.println("proejctService = " + proejctService.selectAllProject());


        String resultlistType = "";
        switch (listType.orElse("")){
            case "all" :
                resultlistType = "전체조회";
                break;
            case "screening" :
                resultlistType = "심사중";
                break;
            case "processing" :
                resultlistType = "진행중";
                break;
            case "refuse" :
                resultlistType = "반려";
                break;
            case "cancel" :
                resultlistType = "취소";
                break;
            default:
                resultlistType = "전체조회";
        }

        List<ProjectDTO> projectList = proejctService.selectAllByAccount(1);

        model.addAttribute("listType",resultlistType);
        model.addAttribute("userName","seller01"); // 사용자명
        model.addAttribute("search",search);
        model.addAttribute("projectList",projectList);

        System.out.println("proejctService = " + projectList );
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
    public String getProjectQnAList(@RequestParam(required = false) String listType,
                                    @RequestParam(required = false) String searchType,
                                    @RequestParam(required = false) String search,
                                    Model model){
        System.out.println("listType = " + listType);
        System.out.println("searchType= " + searchType);
        System.out.println("search = " + search);

        String resultlistType = "";

        switch (listType){
            case "all" :
                resultlistType = "전체조회";
                break;
            case "wait" :
                resultlistType = "답변대기중";
                break;
            case "complete" :
                resultlistType = "완료";
                break;
        }

        model.addAttribute("listType", resultlistType);
        model.addAttribute("userName","seller01"); // 사용자명
        model.addAttribute("search",search);
        model.addAttribute("searchType",searchType);
        return "/seller/sellerqa";
    }

}
