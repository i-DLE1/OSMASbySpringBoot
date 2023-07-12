package com.idle.osmas.seller.controller;

import com.idle.osmas.member.dto.UserImpl;
import com.idle.osmas.seller.dto.ProjectCategoryDTO;
import com.idle.osmas.seller.dto.ProjectDTO;
import com.idle.osmas.seller.dto.ProjectWishDTO;
import com.idle.osmas.seller.service.ProjectCategoryService;
import com.idle.osmas.seller.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Controller
@RequestMapping("/*")
public class SaleListController {

    private final ProjectService projectService;

    private final ProjectCategoryService projectCategoryService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public SaleListController(ProjectService projectService,
                              ProjectCategoryService projectCategoryService) {

        this.projectService = projectService;
        this.projectCategoryService = projectCategoryService;
    }

    @GetMapping("saleList")
    public String salesList(@RequestParam(required = false) String search,
                            Model model){

        List<ProjectCategoryDTO> categoryList = projectCategoryService.selectByCategoryType(null);

        model.addAttribute("categoryList",categoryList);

        return "common/projectListView";
    }

    @GetMapping("/getSubCategory")
    @ResponseBody
    public List<ProjectCategoryDTO> getSubCategory(@RequestParam(value = "no", required = false) Integer mainCategory){
        System.out.println("mainCategory = " + mainCategory);
        return projectCategoryService.selectByCategoryType(mainCategory);
    }

    @GetMapping("/getSaleList")
    @ResponseBody
    public List<Map<String, String>> getSaleList(@RequestParam(required = false) List<Integer> categoryCode,
                                                 @RequestParam(required = false) String searchTitle,
                                                 @RequestParam(required = false) int startNo,
                                                 @RequestParam(required = false) Boolean today,
                                                 @RequestParam(required = false) Boolean openExpect,
                                                 Principal principal
                                                 ){

        List<ProjectDTO> tempProjectList = new ArrayList<>();
        List<ProjectCategoryDTO> categorySubList;
        Map<String, Object> projectParams = new HashMap<>();

        projectParams.put("startNo",startNo);
        projectParams.put("endNo",startNo+12);

        if(today != null && today){
            projectParams.put("today", today);
        }

        if(openExpect != null && openExpect){
            System.out.println("openExpect = " + openExpect);
            projectParams.put("openExpect", openExpect);
        }

        if(categoryCode == null || categoryCode.size() == 0){
            projectParams.put("searchTitle",searchTitle);
            tempProjectList = projectService.selectProjectByCategory(projectParams);
        }else {
            if (categoryCode.size() == 1){
                categorySubList = projectCategoryService.selectByCategoryType(categoryCode.get(0));
                categorySubList.forEach(e->categoryCode.add(e.getNo()));
            }

            for(Integer i : categoryCode){
                projectParams.put("categoryNo",i);
                tempProjectList.addAll(projectService.selectProjectByCategory(projectParams));
            }
        }


        List<Map<String, String>> attrList = new ArrayList<>();

        DecimalFormat df = new DecimalFormat("###,###");

        List<ProjectWishDTO> projectWishList = null;

        if(principal != null){
            UserImpl user = (UserImpl)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
            projectWishList = projectService.selectProjectWishByNo(user.getNo(), null);
        }


        for(ProjectDTO project : tempProjectList){
            Map<String, String> attr = new HashMap<>();

            long betweenDays = ChronoUnit.DAYS.between(LocalDate.now(),project.getEndDate());
            long startDays = ChronoUnit.DAYS.between(LocalDate.now(),project.getStartDate());

            attr.put("no",String.valueOf(project.getNo()));
            attr.put("title", project.getTitle());
            attr.put("currentAmount", df.format(project.getCurrentAmount())+"원");
            attr.put("date", String.valueOf(betweenDays));
            attr.put("startDate", String.valueOf(startDays));

            attr.put("views", project.getViews().toString());
            if(project.getStartDate().isEqual(LocalDate.now())){
                attr.put("today","true");
            }else {
                attr.put("today","false");
            }

            if(openExpect != null && openExpect) attr.put("openExpect","true");


            if(projectWishList != null){
                for (ProjectWishDTO e : projectWishList) {
                    if (project.getNo() == e.getRefProjectNo()) attr.put("favorite", "true");
                }
            }

            if(project.getProjectFileList().size() > 0) {
                attr.put("img", "/files/"+ "project/" + project.getNo() + "/" + project.getProjectFileList().get(0).getChangeName());
            }

            attrList.add(attr);
        }

        return attrList;
    }

    @GetMapping("projectFavorite")
    @ResponseBody
    public String projectFavorite(@RequestParam int no,
                               @RequestParam boolean isActive,
                               Principal principal){

        if(principal == null) return "noAccount";

        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        if(isActive){
            projectService.insertProjectWish(user.getNo(), no);
            return "insertSuccess";
        }else {
            projectService.deleteProjectWish(user.getNo(), no);
            return "deleteSuccess";
        }
    }
}
