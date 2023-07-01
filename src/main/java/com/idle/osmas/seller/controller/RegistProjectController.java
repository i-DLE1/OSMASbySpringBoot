package com.idle.osmas.seller.controller;

import com.idle.osmas.seller.dto.*;
import com.idle.osmas.seller.service.RegistProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.templateparser.raw.IRawHandler;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/seller/regist/*")
public class RegistProjectController {

    private final RegistProjectService registProjectService;

    private final ImageFileController imageFileController;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public RegistProjectController(RegistProjectService registProjectService, ImageFileController imageFileController) {
        this.registProjectService = registProjectService;
        this.imageFileController = imageFileController;
    }

    @GetMapping("getSubCategory")
    @ResponseBody
    public List<CategoryDTO> getSubCategory(@RequestParam Integer mainCategoryCode){

        List<CategoryDTO> subCategory = registProjectService.selectByCategoryType(mainCategoryCode);
        log.info("subCategory = " + subCategory);
        return subCategory;
    }

    @GetMapping("project1")
    public String getProjectTerms(@RequestParam(required = false) Integer id, Model model){
        log.info("id = " + id);
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
    public String getProjectRegist(@RequestParam(required = false) Integer id, Model model, Principal userId){

//        ProjectDTO tempProject = registProjectService.selectTemporaryByUserId(userId.getName());
        int projectNo = registProjectService.selectTemporaryProjectNoByUserId("admin01");
        ProjectDTO tempProject = registProjectService.selectTemporaryProjectInfoByProjectNo(projectNo);
        List<CategoryDTO> categoryList = registProjectService.selectByCategoryType(null);

        if(tempProject != null) {
            model.addAttribute("temporaryProject", tempProject);
        }
        model.addAttribute("mainCategory",categoryList);
        return "/seller/regist/registProject2";
    }

    @PostMapping(value = "project2")
    @ResponseBody
    public String postProjectIRegist(@RequestBody ProjectDTO project, Model model){

            String title = project.getTitle();
            Optional<LocalDate> startDate = Optional.ofNullable(project.getStartDate());
            Optional<LocalDate> endDate = Optional.ofNullable(project.getEndDate());
            Optional<Long> targetAmount = Optional.ofNullable(project.getTargetAmount());
            Optional<Integer> category = Optional.ofNullable(project.getCategory().getNo());

            if (title.length() == 0 ) return "fail";
            if (startDate.isEmpty() ) return "fail";
            if (endDate.isEmpty() ) return "fail";
            if (category.isEmpty()) return "fail";
            if (targetAmount.isEmpty() || targetAmount.get() < 100000 ) return "fail";

            project.setProjectProgress(ProjectProgressDTO.builder().status(ProjectProgressStatus.TEMPORARY).build());
            registProjectService.temporaryInsertProject(project);

            return "success";
    }

    @GetMapping("project3")
    public String getProjectProductRegist(@RequestParam(required = false) Integer id, Model model, Principal user){
        log.info("id = " + id);
        return "/seller/regist/registProject3";
    }
    @GetMapping("project3ProductGetdata")
    @ResponseBody
    public List<ProductDTO> getProjectProductData(){
        Integer projectNo = registProjectService.selectTemporaryProjectNoByUserId("admin01");

        log.info("project = " + projectNo);

        return registProjectService.selectTemporaryProductListByProjectNo(projectNo);
    }
    @GetMapping("project3ProductGetImg")
    @ResponseBody
    public List<ProjectFileDTO> getProjectImg(){
        Integer projectNo = registProjectService.selectTemporaryProjectNoByUserId("admin01");

        log.info("project = " + projectNo);

        return registProjectService.selectTemporaryProjectFileListByProjectNo(projectNo);
    }

    @PostMapping(value = "project3")
    @ResponseBody
    public String postProjectProductRegist(@RequestPart("productList") Map<String, List<ProductDTO>> productList,
                                           @RequestPart(required = false) MultipartFile presentFile,
                                           @RequestPart(required = false) MultipartFile thumbnailFile,
                                           @RequestPart(required = false) List<MultipartFile> fileList,
                                           Model model, Principal user){

        try {

            imageFileController.registFile(ProjectFileType.REPRESENT, presentFile);
            imageFileController.registFile(ProjectFileType.THUMBNAIL, thumbnailFile);
            for (MultipartFile file : fileList) {
                imageFileController.registFile(ProjectFileType.CONTENT, file);
            }

        } catch (Exception e) {
            log.info("저장 실패"+e);
        }

        List<ProductDTO> deleteProductList = productList.get("old")
                .stream().filter(oldE-> productList.get("new")
                        .stream().noneMatch(newE -> oldE.getNo() == newE.getNo()) )
                .collect(Collectors.toList());

        Integer projectNo = registProjectService.selectTemporaryProjectNoByUserId("admin01");


        if(projectNo == null){
            // 수정 페이지
        }else {
            registProjectService.deleteProjectProduct(deleteProductList);
            registProjectService.temporaryInsertProjectProduct(productList.get("new"),"admin01");
        }

        return "success";
    }

    @GetMapping("project4")
    public String getProjectProductDetail(@RequestParam(required = false) Integer id, Model model){
        Integer projectNo = registProjectService.selectTemporaryProjectNoByUserId("admin01");
        if(projectNo != null){
            ProjectDTO project = registProjectService.selectTemporaryProjectInfoByProjectNo(projectNo);
            model.addAttribute("project" ,project); // 웹에디터 기본값
        }
        return "/seller/regist/registProject4";
    }
    
    @PostMapping("project4")
    @ResponseBody
    public String postProjectProductDetail(@RequestBody ProjectDTO project, Model model){
        log.info("project.content = " + project);
        registProjectService.updateProjectContent(project);
        return "success";
    }

    @GetMapping("project5")
    public String getProjectFAQ(@RequestParam(required = false) Integer id, Model model){


        return "/seller/regist/registProject5";
    }
    @GetMapping("project5GetData")
    @ResponseBody
    public List<ProjectFAQDTO> getProjectFaqData(@RequestParam(required = false) Integer id){
        Integer projectNo = registProjectService.selectTemporaryProjectNoByUserId("admin01");
        registProjectService.selectTemporaryProjectFaqByProjectNo(projectNo);
        return registProjectService.selectTemporaryProjectFaqByProjectNo(projectNo);
    }


    @PostMapping("project5")
    @ResponseBody
    public String postProjectFAQ(@RequestBody Map<String,List<ProjectFAQDTO>> projectFAQList, Model model){
        Integer projectNo = registProjectService.selectTemporaryProjectNoByUserId("admin01");

        List<ProjectFAQDTO> deleteProjectFaq = projectFAQList.get("old")
                .stream().filter(oldE-> projectFAQList.get("new")
                        .stream().noneMatch(newE -> oldE.getNo() == newE.getNo()) )
                .collect(Collectors.toList());
        log.info("deleteProjectFaq = " + deleteProjectFaq);

        if(deleteProjectFaq != null) registProjectService.deleteProjectFAQ(deleteProjectFaq);

        log.info("projectFAQList = " + projectFAQList);
        projectFAQList.get("new").forEach(e->{
            if(e.getNo() == 0) {
                registProjectService.insertProjectFAQ(projectNo, e);
            }else {
                registProjectService.updateProjectFAQ(e);
            }


        });
        return "success";
    }


    @GetMapping("project6")
    public String getProjectNEWS(@RequestParam(required = false) Integer id, Model model){
        System.out.println("id = " + id);
        model.addAttribute("newsList","test");
        return "/seller/regist/registProject6";
    }

    @GetMapping("project6GetData")
    @ResponseBody
    public List<ProjectNewsDTO> getProjectNEWSData(@RequestParam(required = false) Integer id){
        int projectNo = registProjectService.selectTemporaryProjectNoByUserId("admin01");
        List<ProjectNewsDTO> projectNewsList= registProjectService.selectProjectNewsListByProjectNo(projectNo);

        projectNewsList.stream().map(e->{
            String afterString = e.getContent()
                    .replaceAll("<([^>]+)>", "")
                    .replaceAll("&nbsp;", "");
//                    .substring(0,14);
            e.setContent(afterString);
            return e;
        }).collect(Collectors.toList());
        return projectNewsList;
    }

    @GetMapping("projectNews")
    @ResponseBody
    public ProjectNewsDTO ProjectNews(Integer no){
        return registProjectService.selectProjectNewsByProjectNewsNo(no);
    }

    @GetMapping("deleteProjectNews")
    @ResponseBody
    public String deletePojectNews(Integer no){
        System.out.println("no = " + no);
        int result = registProjectService.deleteProjectNews(no);
        if(result > 0){
            return "success";
        } else {
            return "fail";
        }
    }

    @PostMapping("modifyProjectNews")
    @ResponseBody
    public String modifyPojectNews(@RequestBody  ProjectNewsDTO projectNews){
        System.out.println("projectNews = " + projectNews);
        int result = registProjectService.updateProjectNews(projectNews);
        if(result > 0){
            return "success";
        } else {
            return "fail";
        }
    }


    @PostMapping("project6")
    @ResponseBody
    public String postProjectNEWS(@RequestBody ProjectNewsDTO projectNewsDTO, Integer id, Model model){
        System.out.println("projectNewsDTO = " + projectNewsDTO);
        Integer projectNo = registProjectService.selectTemporaryProjectNoByUserId("admin01");
        registProjectService.insertProjectNews(projectNo, projectNewsDTO);
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
