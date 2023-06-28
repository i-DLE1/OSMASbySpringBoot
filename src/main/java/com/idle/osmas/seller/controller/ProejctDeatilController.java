package com.idle.osmas.seller.controller;

import com.idle.osmas.seller.dto.ProjectQnAAnswerDTO;
import com.idle.osmas.seller.dto.ProjectQnADTO;
import com.idle.osmas.seller.service.SellerPageService;
import com.idle.osmas.seller.service.SellerPageServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/seller/projectDetail/*")
public class ProejctDeatilController {
    private final SellerPageServiceImpl sellerPageService;

    public ProejctDeatilController(SellerPageServiceImpl sellerPageService) {
        this.sellerPageService = sellerPageService;
    }

    @GetMapping("projectDetail")
    public String projectDetail(@RequestParam String id){
        return "/seller/popup/projectDetail";
    }

    @GetMapping("cancel")
    public String cacnel(@RequestParam String id){
        return "/seller/popup/cancel";
    }
    @GetMapping("qaAnswer")
    public String getQaAnswer(@RequestParam String id, Model model) {

        System.out.println("id = " + id);

        ProjectQnADTO projectQnA = sellerPageService.selectByQnANo(Integer.valueOf(id));

        System.out.println("projectQnAAnswer = " + projectQnA);

        model.addAttribute("projectQnA", projectQnA);
        return "/seller/popup/qa_answer";
    }
    @PostMapping("qaAnswer")
    @ResponseBody
    public String postQaAswer(@RequestBody String content,
                              @RequestParam Integer id,
                              @RequestParam String submitType,
                              Model model){
        String result = "";

        Map<String, Object> qnaData = new HashMap<>();
        qnaData.put("no", id);
        qnaData.put("content", content);
        qnaData.put("memberId","admin01");
        if("regist".equals(submitType)){
            sellerPageService.insertProjectQnAAnswer(qnaData);
            System.out.println("쓰기 성공");
        }else if("modify".equals(submitType)){
            sellerPageService.updateProjectQnAAnswer(qnaData);
            System.out.println("수정 성공");
        }

        return result;
    }


    @GetMapping("refuse")
    public String refuse(@RequestParam String id){
        return "/seller/popup/refuse";
    }

    @GetMapping("retry")
    public String retry(@RequestParam String id){
        return "/seller/popup/retry";
    }
}
