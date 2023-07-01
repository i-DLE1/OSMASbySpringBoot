package com.idle.osmas.seller.controller;

import com.idle.osmas.seller.dto.*;
import com.idle.osmas.seller.service.RegistProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.standard.expression.OrExpression;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/seller/regist/*")
public class RegistProjectController {



    @Value("${customSaveFileDirectoryPath}")
    String savedFileDriectoryPath;
    private final RegistProjectService registProjectService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public RegistProjectController(RegistProjectService registProjectService) {
        this.registProjectService = registProjectService;
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
        ProjectDTO tempProject = registProjectService.selectTemporaryByUserId("admin01");
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
        ProjectDTO tempProject = registProjectService.selectTemporaryByUserId("admin01");
        log.info("tempProject = " + tempProject);

        // 파일 다시 클라로 표시하기

        return tempProject.getProductList();
    }


    public void registFile(ProjectFileType fileType, MultipartFile file) throws IOException {
        if(file.getSize() > 0){
            String originFileName = file.getOriginalFilename();

            log.info("originFileName = " + originFileName);

            String ext = originFileName.substring(originFileName.lastIndexOf("."));

            log.info("ext = " + ext);

            String savedFileName = UUID.randomUUID().toString().replace("-","")+ext;

            File savedFile = new File(savedFileDriectoryPath+"/"+savedFileName);
            file.transferTo(savedFile);

            int result = 0;

            ProjectDTO tempProject = registProjectService.selectTemporaryByUserId("admin01");
            result = registProjectService.insertProjectFile(fileType,originFileName,savedFileName,"N",tempProject.getNo());

            if (result > 0){
                log.info("파일이 정상으로 저장 됐습니다.");
            }else {
                log.info("정상적으로 ");
                savedFile.delete();
            }

        }
    }


    @PostMapping(value = "project3")
    @ResponseBody
    public String postProjectProductRegist(@RequestPart("productList") Map<String, List<ProductDTO>> productList,
                                           MultipartFile presentFile,
                                           MultipartFile thumbnailFile,
                                           List<MultipartFile> fileList,
                                           Model model, Principal user){

        try {
            registFile(ProjectFileType.REPRESENT, presentFile);
            registFile(ProjectFileType.THUMBNAIL, thumbnailFile);
            for (MultipartFile file : fileList) {
                registFile(ProjectFileType.CONTENT, file);
            }

        } catch (IOException e) {
            log.info("저장 실패"+e);

            throw new RuntimeException(e);
        }

        List<ProductDTO> deleteProductList = productList.get("old")
                .stream().filter(oldE-> productList.get("new")
                        .stream().noneMatch(newE -> oldE.getNo() == newE.getNo()) )
                .collect(Collectors.toList());

        ProjectDTO tempProject = registProjectService.selectTemporaryByUserId("admin01");


        if(tempProject == null){
            // 수정 페이지
        }else {
            registProjectService.deleteProjectProduct(deleteProductList);
            registProjectService.temporaryInsertProjectProduct(productList.get("new"),"admin01");

        }

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
