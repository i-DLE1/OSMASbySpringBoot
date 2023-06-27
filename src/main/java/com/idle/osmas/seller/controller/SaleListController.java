package com.idle.osmas.seller.controller;

import com.idle.osmas.seller.dto.ProjectCategoryDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/*")
public class SaleListController {

    @GetMapping("salesList")
    public String salesList(@RequestParam(required = false) String search,
                            Model model){

        List<ProjectCategoryDTO> categoryList = new ArrayList<>();
        ProjectCategoryDTO categoryDTO = new ProjectCategoryDTO();

        categoryList.add(new ProjectCategoryDTO(1,null,"카테1",null));
        categoryList.add(new ProjectCategoryDTO(2,null,"카테2",null));
        categoryList.add(new ProjectCategoryDTO(3,null,"카테3",null));
        categoryList.add(new ProjectCategoryDTO(4,null,"카테4",null));
        categoryList.add(new ProjectCategoryDTO(5,null,"카테5",null));

        System.out.println("search = " + search);

        model.addAttribute("categoryList",categoryList);

        return "common/projectListView";
    }

    @GetMapping("/getSaleList")
    @ResponseBody
    public List<Map<String, String>> getSaleList(@RequestParam List<Integer> categoryCode){

        Map<String, String> attr = new HashMap<>();
        List<Map<String, String>> attrList = new ArrayList<>();

        attr.put("img", "/images/seller/project/item1.png");
        attr.put("money", "10000");
        attr.put("date", "12");
        attr.put("title", "타이틀");
        attr.put("user", "1001");
        attrList.add(attr);

        System.out.println("attrList = " + attrList);
        return attrList;
    }
}
