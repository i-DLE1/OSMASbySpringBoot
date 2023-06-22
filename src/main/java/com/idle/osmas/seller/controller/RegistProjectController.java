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
    public String projectRegist(){
        return "/seller/regist/registProject2";
    }
    @PostMapping("project2")
    @ResponseBody
    public  String projectInitRegist(ProjectDTO project, Model model){
        System.out.println("project = " + project);
        return "success";
    }

    @GetMapping("project3")
    public String projectDetail(){
        return "/seller/regist/registProject3";
    }
}
