package com.idle.osmas.seller.controller;

import com.idle.osmas.seller.dto.CategoryDTO;
import com.idle.osmas.seller.dto.ProjectCategoryDTO;
import com.idle.osmas.seller.dto.ProjectDTO;
import com.idle.osmas.seller.service.ProjectCategoryService;
import com.idle.osmas.seller.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Controller
@RequestMapping("/*")
public class SaleListController {

    private final ProjectService projectService;

    private final ProjectCategoryService projectCategoryService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public SaleListController(ProjectService projectService, ProjectCategoryService projectCategoryService) {
        this.projectService = projectService;
        this.projectCategoryService = projectCategoryService;
    }

    @GetMapping("salesList")
    public String salesList(@RequestParam(required = false) String search,
                            Model model){

        List<ProjectCategoryDTO> categoryList = projectCategoryService.selectByCategoryType(null);

        System.out.println("search = " + search);

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
                                                 @RequestParam(required = false) String searchTitle){
        System.out.println("categoryCode = " + categoryCode);

        List<ProjectDTO> tempProjectList = new ArrayList<>();
        List<ProjectCategoryDTO> categorySubList = new ArrayList<>();
        Map<String, Object> projectParams = new HashMap<>();

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

        System.out.println("tempProjectList = " + tempProjectList);

        List<Map<String, String>> attrList = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("###,###");
        for(ProjectDTO project : tempProjectList){
            Map<String, String> attr = new HashMap<>();
            Period betweenDays = Period.between(LocalDate.now(),project.getEndDate());
            System.out.println("betweenDays = " + betweenDays);
            attr.put("no",String.valueOf(project.getNo()));
            attr.put("title", project.getTitle());
            attr.put("currentAmount", df.format(project.getCurrentAmount()));
            attr.put("date", String.valueOf(betweenDays.getDays()) );
            attr.put("img", "/files/seller/project/" + project.getProjectFileList().get(0).getChangeName());
            attr.put("views", project.getViews().toString());
            attrList.add(attr);
        }

        log.info("attrList = " + attrList);
        return attrList;
    }
}
