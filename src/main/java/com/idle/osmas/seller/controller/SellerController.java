package com.idle.osmas.seller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/seller/")
public class SellerController {

    @GetMapping(value = {"/","/projectList"})
    public String getProjectList(@RequestParam(required = false) String listType,
                                 @RequestParam(required = false) String searchType,
                                 @RequestParam(required = false) String search){
        System.out.println("listType = " + listType);
        System.out.println("searchType = " + searchType);
        System.out.println("search = " + search);

        return "/seller/sellerProjectList";
    }

    @GetMapping(value = {"/","/orderList"})
    public String getOrderList(@RequestParam(required = false) String listType){
        System.out.println("listType = " + listType);
        return "/seller/sellerOrderList";
    }

    @GetMapping("/projectQnAList")
    public String getProjectQnAList(@RequestParam(required = false) String listType,
                                    @RequestParam(required = false) String searchType,
                                    @RequestParam(required = false) String search){
        System.out.println("listType = " + listType);
        System.out.println("searchType= " + searchType);
        System.out.println("search = " + search);
        return "/seller/sellerqa";
    }

}
