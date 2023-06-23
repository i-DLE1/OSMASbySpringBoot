package com.idle.osmas.seller.controller;

import com.idle.osmas.seller.dto.ProjectDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Date;

@Controller
@RequestMapping("/seller/regist/*")
public class RegistProjectController {

    @GetMapping("project1")
    public String getProjectTerms(){
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
    public String getProjectRegist(){
        return "/seller/regist/registProject2";
    }
    @PostMapping(value = "project2")
    @ResponseBody
    public String postProjectIRegist(@RequestBody ProjectDTO project, Model model){
        System.out.println("project = " + project);
        return "success";
    }

    @GetMapping("project3")
    public String getProjectDetail(){
        return "/seller/regist/registProject3";
    }

    @GetMapping("project4")
    public String getProjectProductDetail(){
        return "/seller/regist/registProject4";
    }

    @GetMapping("project5")
    public String getProjectFAQ(){
        return "/eller/regist/registProject5";
    }


    @GetMapping("project6")
    public String getProjectNEWS(){
        return "/eller/regist/registProject6";
    }

    @GetMapping("project7")
    public String getProjectInfo(){
        return "/eller/regist/registProject7";
    }

}
