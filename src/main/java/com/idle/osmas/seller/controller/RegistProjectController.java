package com.idle.osmas.seller.controller;

import com.idle.osmas.seller.dto.*;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/seller/regist/*")
public class RegistProjectController {

    @GetMapping("project1")
    public String getProjectTerms(@RequestParam(required = false) Integer id, Model model){
        System.out.println("id = " + id);
        String term1 = "약관1";
        String term2 = "약관2";
        model.addAttribute("term1", term1);
        model.addAttribute("term2", term2);
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

            String title = project.getTitle();
            Optional<LocalDate> startDate = Optional.ofNullable(project.getStartDate());
            Optional<LocalDate> endDate = Optional.ofNullable(project.getEndDate());
            Optional<Long> targetAmount = Optional.ofNullable(project.getTargetAmount());

            if (title.length() == 0 ) return "fail";
            if (startDate.isEmpty() ) return "fail";
            if (endDate.isEmpty() ) return "fail";
            if (targetAmount.isEmpty() || targetAmount.get() < 100000 ) return "fail";

            return "success";
    }

    @GetMapping("project3")
    public String getProjectProductRegist(@RequestParam(required = false) Integer id, Model model){
        System.out.println("id = " + id);

        return "/seller/regist/registProject3";
    }
    
    @PostMapping(value = "project3")
    @ResponseBody
    public String postProjectProductRegist(@RequestBody List<ProductDTO> projectList, Model model){
        projectList.forEach(e->{
            System.out.println("project = " + e);
        });
        return "success";
    }

    @GetMapping("project4")
    public String getProjectProductDetail(@RequestParam(required = false) Integer id, Model model){
        System.out.println("id = " + id);
        model.addAttribute("content","<h1>text</h1>"); // 웹에디터 기본값
        return "/seller/regist/registProject4";
    }
    
    @PostMapping("project4")
    @ResponseBody
    public String postProjectProductDetail(@RequestBody ProjectDTO project, Model model){
        System.out.println("project.content = " + project.getContent());

        return "success";
    }

    @GetMapping("project5")
    public String getProjectFAQ(@RequestParam(required = false) Integer id, Model model){
        System.out.println("id = " + id);


        return "/seller/regist/registProject5";
    }

    @PostMapping("project5")
    @ResponseBody
    public String postProjectFAQ(@RequestBody List<ProjectFAQDTO> projectFAQList, Model model){
        System.out.println("projectFAQList = " + projectFAQList);
        projectFAQList.forEach(e->{
            System.out.println("e = " + e);
        });
        return "success";
    }


    @GetMapping("project6")
    public String getProjectNEWS(@RequestParam(required = false) Integer id, Model model){
        System.out.println("id = " + id);
        model.addAttribute("newsList","test");
        return "/seller/regist/registProject6";
    }
    @PostMapping("project6")
    @ResponseBody
    public String postProjectNEWS(@RequestBody ProjectNewsDTO projectNewsDTO, Model model){
        System.out.println("projectNewsDTO = " + projectNewsDTO);
        return "success";
    }

    @GetMapping("project7")
    public String getProjectInfo(@RequestParam(required = false) Integer id, Model model){
        System.out.println("id = " + id);
        model.addAttribute("projectInfo","test1");
        model.addAttribute("productInfo", "test");
        return "/seller/regist/registProject7";
    }

    @PostMapping("project7")
    public String postProjectInfo(Model model){
        System.out.println("프로젝트 마지막 저장");

        // proejct 상태 변경
        return "success";
    }

}
