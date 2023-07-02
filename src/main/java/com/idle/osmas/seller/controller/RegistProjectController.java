package com.idle.osmas.seller.controller;

import com.idle.osmas.common.exception.AccessAuthorityException;
import com.idle.osmas.member.dto.MemberDTO;
import com.idle.osmas.seller.dto.*;
import com.idle.osmas.seller.service.ProjectProgressService;
import com.idle.osmas.seller.service.RegistProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.security.Principal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/seller/regist/*")
public class RegistProjectController {

    private final RegistProjectService registProjectService;

    private final ProjectProgressService projectProgressService;

    private final ImageFileController imageFileController;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final DecimalFormat moneyStringFormat = new DecimalFormat("###,###");

    public RegistProjectController(RegistProjectService registProjectService, ProjectProgressService projectProgressService, ImageFileController imageFileController) {
        this.registProjectService = registProjectService;
        this.projectProgressService = projectProgressService;
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
    public String getProjectTerms(@RequestParam(required = false) Integer no, Model model, Principal principal){
        if(no == null){
//            no = registProjectService.selectTemporaryProjectNoByUserId(principal.getName());
            no = registProjectService.selectTemporaryProjectNoByUserId("admin01");
        }

        String term1 = "약관1";
        String term2 = "약관2";

        model.addAttribute("term1", term1);
        model.addAttribute("term2", term2);

        if(no !=null){
            model.addAttribute("check1",true);
            model.addAttribute("check2",true);
        }else {
            model.addAttribute("check1",false);
            model.addAttribute("check2",false);
        }

        return "/seller/regist/registProject1";

    }
    @PostMapping("project1")
    @ResponseBody
    public String postProjectTerms(Model model
            , @RequestParam boolean check1, @RequestParam boolean check2, Principal principal) throws AccessAuthorityException {

        String result = "";

        if(check1 && check2){
            MemberDTO member = new MemberDTO();
//            member.setNo(registProjectService.selectMemberNoById(principal.getName()));

            int memberNo = registProjectService.selectMemberNoById("admin01");

            member.setNo(memberNo);

            System.out.println("member = " + member);
            ProjectDTO tempProject;
            tempProject = ProjectDTO.builder()
                    .member(member)
                    .build();
            registProjectService.insertTemporaryProject(tempProject);

            projectProgressService.insertProjectProgressStatus(
                    ProjectProgressDTO.builder()
                            .content("")
                            .status(ProjectProgressStatus.TEMPORARY)
                            .refProjectNo(tempProject.getNo())
                            .build());
            result = "success";
        }else{
            result = "fail";
        }

        return result;
    }

    @GetMapping("project2")
    public String getProjectInfo(@RequestParam(required = false) Integer no, Model model, Principal principal) throws AccessAuthorityException {

        System.out.println("principal = " + principal);

        List<CategoryDTO> categoryList = registProjectService.selectByCategoryType(null);

        if(no == null){
//            no = registProjectService.selectTemporaryProjectNoByUserId(principal.getName());
            no = registProjectService.selectTemporaryProjectNoByUserId("admin01");
        }

        ProjectDTO project = null;

        if(no != null) {
//            ProjectDTO project = registProjectService.selectProjectInfoByProjectNo(no,principal.getName());
            project = registProjectService.selectProjectInfoByProjectNo(no,"admin01");
        }

        if(project == null ) throw new AccessAuthorityException("접근 권한이 없습니다.");
        
        model.addAttribute("project", project);
        model.addAttribute("mainCategory",categoryList);

        return "/seller/regist/registProject2";
    }

    @PostMapping(value = "project2")
    @ResponseBody
    public String postProjectIRegist(@RequestBody ProjectDTO project, Model model,Principal principal){

        System.out.println("principal = " + principal);
        if (project == null) return "fail";

        String title = project.getTitle();
        LocalDate startDate = project.getStartDate();
        LocalDate endDate = project.getEndDate();
        Long targetAmount = project.getTargetAmount();
        Integer category = project.getCategory().getNo();

        if (title.length() == 0 ) return "fail";
        if (startDate == null ) return "fail";
        if (endDate == null ) return "fail";
        if (category == null) return "fail";
        if (targetAmount == null || targetAmount < 100000 ) return "fail";

        project.setProjectProgress(ProjectProgressDTO.builder().status(ProjectProgressStatus.TEMPORARY).build());
        registProjectService.insertProject(project);

        return "success";
    }

    @GetMapping("project3")
    public String getProjectProductRegist(@RequestParam(required = false) Integer no, Model model, Principal principal) throws AccessAuthorityException {

        boolean existProject = false;

        if(no == null){
//            no = registProjectService.selectTemporaryProjectNoByUserId(principal.getName());
            no = registProjectService.selectTemporaryProjectNoByUserId("admin01");
        }

        if(no != null) {
//            project = registProjectService.selectProjectByProjectNo(no.get(), principal.getName());
            existProject = registProjectService.existProjectByProjectNo(no, "admin01") ;
        }

        if (!existProject) throw new AccessAuthorityException("수정할 프로젝트가 존재하지 않습니다.");


        return "/seller/regist/registProject3";
    }
    @GetMapping("project3ProductGetdata")
    @ResponseBody
    public List<ProductDTO> getProjectProductData(@RequestParam(required = false) Integer no){
        if(no == null){
            no = registProjectService.selectTemporaryProjectNoByUserId("admin01");
        }
        List<ProductDTO> productList = new ArrayList<>();
        productList = registProjectService.selectProductListByProjectNo(no,null);
        if(productList.size() == 0){
            productList.add(ProductDTO.builder().no(0).name("").introduction("").size("").build());
        }
        return productList;
    }
    @GetMapping("project3ProductGetImg")
    @ResponseBody
    public List<ProjectFileDTO> getProjectImg(@RequestParam(required = false) Integer no){
        if(no == null){
            no = registProjectService.selectTemporaryProjectNoByUserId("admin01");
        }

        if(no == null){
            List<ProjectFileDTO> templist = new ArrayList<>();
            templist.add(ProjectFileDTO.builder().build());
            return templist;
        }else {
            return registProjectService.selectProjectFileListByProjectNo(no,null);
        }
    }

    @PostMapping(value = "project3")
    @ResponseBody
    public String postProjectProductRegist(@RequestPart Map<String, List<ProductDTO>> productList,
                                           @RequestPart(required = false) MultipartFile presentFile,
                                           @RequestPart(required = false) MultipartFile thumbnailFile,
                                           @RequestPart(required = false) List<MultipartFile> fileList,
                                           @RequestParam(required = false) Integer no, Principal principal){

        if(no == null) {
//            no = registProjectService.selectTemporaryProjectNoByUserId(principal.getName());
            no = registProjectService.selectTemporaryProjectNoByUserId("admin01");
        }

        if(no == null) return "fail";

        List<ProductDTO> deleteProductList = productList.get("old")
                .stream().filter(oldE-> productList.get("new")
                        .stream().noneMatch(newE -> oldE.getNo() == newE.getNo()) )
                .collect(Collectors.toList());

        registProjectService.deleteProjectProduct(deleteProductList);
//        registProjectService.insertProjectProduct(productList.get("new"),principal.getName(),no);
        registProjectService.insertProjectProduct(productList.get("new"),"admin01",no);

        try {
            imageFileController.registFile(ProjectFileType.REPRESENT, presentFile);
            imageFileController.registFile(ProjectFileType.THUMBNAIL, thumbnailFile);

            for (MultipartFile file : fileList) {
                imageFileController.registFile(ProjectFileType.CONTENT, file);
            }

        } catch (Exception e) {
            log.info("저장 실패"+e);
            return "fail";
        }

        return "success";
    }

    @GetMapping("project4")
    public String getProjectProductDetail(@RequestParam(required = false) Integer no, Model model,Principal principal) throws AccessAuthorityException {

        if(no == null){
//            no = registProjectService.selectTemporaryProjectNoByUserId(principal.getName());
            no = registProjectService.selectTemporaryProjectNoByUserId("admin01");
        }

        ProjectDTO project = null;
        if(no != null){
//        ProjectDTO project = registProjectService.selectProjectInfoByProjectNo(no,principal.getName());
        project = registProjectService.selectProjectInfoByProjectNo(no,null);
        }

        if (project == null) throw new AccessAuthorityException("등록중인 프로젝트가 없습니다.");

        model.addAttribute("project" ,project); // 웹에디터 기본값
        return "/seller/regist/registProject4";
    }
    
    @PostMapping("project4")
    @ResponseBody
    public String postProjectProductDetail(@RequestBody ProjectDTO project,
                                           @RequestParam(required = false) Integer no, Principal principal){

        if(no == null){
//            no = registProjectService.selectTemporaryProjectNoByUserId(principal.getName());
            no = registProjectService.selectTemporaryProjectNoByUserId("admin01");
        }

        if(no == null) return "fail";

        registProjectService.updateProjectContent(project, no);
        return "success";
    }

    @GetMapping("project5")
    public String getProjectFAQ(@RequestParam(required = false) Integer no, Principal principal) throws AccessAuthorityException {
        if(no == null){
//            no = registProjectService.selectTemporaryProjectNoByUserId(principal.getName());
            no = registProjectService.selectTemporaryProjectNoByUserId("admin01");
        }
        boolean existProject = false;

        if(no != null){
//            project = registProjectService.selectProjectByProjectNo(no,principal.getName());
            existProject = registProjectService.existProjectByProjectNo(no,"amdin01");
        }

        if(!existProject) throw new AccessAuthorityException("수정가는 한 프로젝트가 존재하지 않습니다.");

        return "/seller/regist/registProject5";
    }
    @GetMapping("project5GetData")
    @ResponseBody
    public List<ProjectFAQDTO> getProjectFaqData(@RequestParam(required = false) Integer no, Principal principal){

        if(no == null){
//            no = registProjectService.selectTemporaryProjectNoByUserId(principal.getName());
            no = registProjectService.selectTemporaryProjectNoByUserId("admin01");
        }
        if(no == null){
            List<ProjectFAQDTO> tempProjectFaqList = new ArrayList<>();
            tempProjectFaqList.add(ProjectFAQDTO.builder().no(0).content("").title("").build());
            return tempProjectFaqList;
        }
        return registProjectService.selectProjectFaqByProjectNo(no,null);
    }


    @PostMapping("project5")
    @ResponseBody
    public String postProjectFAQ(@RequestBody Map<String,List<ProjectFAQDTO>> projectFAQList,
                                 @RequestParam(required = false) Integer no, Principal principal){
        if(no == null){
//            no = registProjectService.selectTemporaryProjectNoByUserId(principal.getName());
            no = registProjectService.selectTemporaryProjectNoByUserId("admin01");
        }

        if(no == null) return "fail";

        for (ProjectFAQDTO e : projectFAQList.get("new")) {
            if (e.getNo() == 0) {
                registProjectService.insertProjectFAQ(no, e);
            } else {
                registProjectService.updateProjectFAQ(e);
            }
        }

        List<ProjectFAQDTO> deleteProjectFaq = projectFAQList.get("old")
                .stream().filter(oldE-> projectFAQList.get("new")
                        .stream().noneMatch(newE -> oldE.getNo() == newE.getNo()) )
                .collect(Collectors.toList());
        log.info("deleteProjectFaq = " + deleteProjectFaq);

        if(deleteProjectFaq.size() != 0 ) registProjectService.deleteProjectFAQ(deleteProjectFaq);

        log.info("projectFAQList = " + projectFAQList);


        return "success";
    }


    @GetMapping("project6")
    public String getProjectNEWS(@RequestParam(required = false) Integer no,
                                 Model model, Principal principal) throws AccessAuthorityException {
        if(no == null){
//            no = registProjectService.selectTemporaryProjectNoByUserId(principal.getName());
            no = registProjectService.selectTemporaryProjectNoByUserId("admin01");
        }

        boolean existProject = false;

        if(no != null){
//            existProject = registProjectService.existProjectByProjectNo(no, principal.getName());
            existProject = registProjectService.existProjectByProjectNo(no, "admin01");
        }

        if(!existProject) throw new AccessAuthorityException("등록 및 수정 가능한 프로젝트가 없습니다");

        return "/seller/regist/registProject6";
    }

    @GetMapping("project6GetData")
    @ResponseBody
    public List<ProjectNewsDTO> getProjectNEWSData(@RequestParam(required = false) Integer no,Principal principal){

        if(no == null){
//            no = registProjectService.selectTemporaryProjectNoByUserId(principal.getName());
            no = registProjectService.selectTemporaryProjectNoByUserId("admin01");
        }

        List<ProjectNewsDTO> projectNewsList= registProjectService.selectProjectNewsListByProjectNo(no,null);

        if(projectNewsList.size() == 0) return projectNewsList;

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
        return registProjectService.selectProjectNewsByProjectNewsNo(no,null);
    }

    @GetMapping("deleteProjectNews")
    @ResponseBody
    public String deletePojectNews(Integer no){

        int result = registProjectService.deleteProjectNews(no);

        if(result > 0) return "success";

        return "fail";
    }

    @PostMapping("modifyProjectNews")
    @ResponseBody
    public String modifyPojectNews(@RequestBody  ProjectNewsDTO projectNews){
        int result = registProjectService.updateProjectNews(projectNews);

        if(result > 0) return "success";

        return "fail";

    }


    @PostMapping("project6")
    @ResponseBody
    public String postProjectNEWS(@RequestBody ProjectNewsDTO projectNewsDTO, Integer no, Principal principal){
        System.out.println("principal = " + principal);
        if(no == null){
//            no = registProjectService.selectTemporaryProjectNoByUserId(principal.getName());
            no = registProjectService.selectTemporaryProjectNoByUserId("admin01");
        }

        int result = registProjectService.insertProjectNews(no, projectNewsDTO);

        if(result > 0) return "success";

        return "fail";
    }

    @GetMapping("project7")
    public String getProjectInfo(@RequestParam(required = false) Integer no,
                                 Model model){
//        System.out.println("principal = " + principal);
        if(no == null){
//            no = registProjectService.selectTemporaryProjectNoByUserId(principal.getName());
            no = registProjectService.selectTemporaryProjectNoByUserId("admin01");
        }


        ProjectDTO project = registProjectService.selectProjectByProjectNo(no,null);

        String afterString = project.getContent()
                .replaceAll("<([^>]+)>", "")
                .replaceAll("&nbsp;", "");
        project.setContent(afterString);

        log.info("project = " + project);

        model.addAttribute("projectInfo",project);
        return "/seller/regist/registProject7";
    }

    @GetMapping("projectRegist")
    @ResponseBody
    public String projectRegist(@RequestParam int no, Model model){
        System.out.println("no = " + no);

        ProjectProgressDTO projectProgress;
        projectProgress = ProjectProgressDTO.builder()
                .refProjectNo(no)
                .status(ProjectProgressStatus.SCREENING).content("심사요청").build();

        projectProgressService.insertProjectProgressStatus(projectProgress);
        // proejct 상태 변경
        return "success";
    }



}
