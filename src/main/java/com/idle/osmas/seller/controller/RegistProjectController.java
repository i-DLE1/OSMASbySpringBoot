package com.idle.osmas.seller.controller;

import com.idle.osmas.seller.dto.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/seller/regist/*")
public class RegistProjectController {

    @GetMapping("project1")
    public String getProjectTerms(@RequestParam(required = false) Integer id){
        System.out.println("id = " + id);
        return "/seller/regist/registProject1";
    }
    @PostMapping("project1")
    @ResponseBody
    public String postProjectTerms(Model model
            , @RequestParam boolean check1, @RequestParam boolean check2){

        String result = "";
        if(check1 && check2){
            result = "success";
        }else{
            result = "fail";
        }
        return result;
    }

    @GetMapping("project2")
    public String getProjectRegist(@RequestParam(required = false) Integer id, Model model){
        System.out.println("id = " + id);
        return "/seller/regist/registProject2";
    }
    
    
    @PostMapping(value = "project2")
    @ResponseBody
    public String postProjectIRegist(@RequestBody ProjectDTO project, Model model){
        System.out.println("project = " + project);
        return "success";
    }

    @GetMapping("project3")
    public String getProjectProductRegist(@RequestParam(required = false) Integer id, Model model){
        System.out.println("id = " + id);
        return "/seller/regist/registProject3";
    }
    
    @PostMapping("project3")
    public String postProjectProductRegist(@RequestBody List<ProductDTO> projectList, Model model){
        System.out.println("projectList = " + projectList);
        return "success";
    }

    @GetMapping("project4")
    public String getProjectProductDetail(@RequestParam(required = false) Integer id, Model model){
        System.out.println("id = " + id);
        return "/seller/regist/registProject4";
    }
    
    @PostMapping("project4")
    public String postProjectProductDetail(@RequestBody ProjectDTO project, Model model){
        System.out.println("project = " + project);
        return "success";
    }

    @GetMapping("project5")
    public String getProjectFAQ(@RequestParam(required = false) Integer id, Model model){
        System.out.println("id = " + id);
        return "/seller/regist/registProject5";
    }

    @PostMapping("project5")
    public String postProjectFAQ(@RequestBody List<ProjectFAQDTO> projectFAQList, Model model){
        System.out.println("projectFAQList = " + projectFAQList);
        return "success";
    }


    @GetMapping("project6")
    public String getProjectNEWS(@RequestParam(required = false) Integer id, Model model){
        System.out.println("id = " + id);
        return "/seller/regist/registProject6";
    }
    @PostMapping("project6")
    public String postProjectNEWS(@RequestBody ProjectQnADTO projectQnA, Model model){
        System.out.println("projectQnA = " + projectQnA);
        return "sucess";
    }

    @GetMapping("project7")
    public String getProjectInfo(@RequestParam(required = false) Integer id, Model model){
        System.out.println("id = " + id);
        return "/seller/regist/registProject7";
    }

    @PostMapping("project7")
    public String postProjectInfo(Model model){
        System.out.println("프로젝트 마지막 저장");
        return "success";
    }

}
