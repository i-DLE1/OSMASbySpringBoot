package com.idle.osmas.seller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/seller/")
public class SellerController {

    @GetMapping(value = {"/","/projectList"})
    public String getProjectList(@RequestParam(required = false) String listType){
        System.out.println("listType = " + listType);
        return "/seller/sellerProjectList";
    }

    @GetMapping(value = {"/","/orderList"})
    public String getOrderList(@RequestParam(required = false) String listType){
        System.out.println("listType = " + listType);
        return "/seller/sellerOrderList";
    }

    @GetMapping("/projectQnAList")
    public String getProjectQnAList(@RequestParam(required = false) String listType){
        System.out.println("listType = " + listType);
        return "/seller/sellerqa";
    }
}
