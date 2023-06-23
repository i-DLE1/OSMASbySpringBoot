package com.idle.osmas.seller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/seller/projectDetail/*")
public class ProejctDeatilController {
    @GetMapping("projectDetail")
    public String projectDetail(@RequestParam String id){
        return "/seller/popup/projectDetail";
    }

    @GetMapping("cancel")
    public String cacnel(@RequestParam String id){
        return "/seller/popup/cancel";
    }
    @GetMapping("qaAnwer")
    public String qaAnswer(@RequestParam String id){
        return "/seller/popup/qa_answer";
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
