package com.idle.osmas.seller.controller;

import com.idle.osmas.common.exception.AccessAuthorityException;
import com.idle.osmas.member.dto.MemberDTO;
import com.idle.osmas.seller.dto.*;
import com.idle.osmas.seller.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/seller/regist/*")
public class RegistProjectController {

    private final RegistProjectService registProjectService;

    private final ProjectService projectService;

    private final ProjectProgressService projectProgressService;

    private final ProductService productService;

    private final ProjectFileService projectFileService;

    private final ProjectCategoryService projectCategoryService;
    private final ProjectFAQService projectFAQService;

    private final ProjectNewsService projectNewsService;

    private final ImageFileController imageFileController;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final DecimalFormat moneyStringFormat = new DecimalFormat("###,###");

    public RegistProjectController(RegistProjectService registProjectService, ProjectService projectService, ProjectProgressService projectProgressService, ProductService productService, ProjectFileService projectFileService, ProjectCategoryService projectCategoryService, ProjectFAQService projectFAQService, ProjectNewsService projectNewsService, ImageFileController imageFileController) {
        this.registProjectService = registProjectService;
        this.projectService = projectService;
        this.projectProgressService = projectProgressService;
        this.productService = productService;
        this.projectFileService = projectFileService;
        this.projectCategoryService = projectCategoryService;
        this.projectFAQService = projectFAQService;
        this.projectNewsService = projectNewsService;
        this.imageFileController = imageFileController;
    }

    @GetMapping("getSubCategory")
    @ResponseBody
    public List<ProjectCategoryDTO> getSubCategory(@RequestParam Integer mainCategoryCode){

        List<ProjectCategoryDTO> subCategory = projectCategoryService.selectByCategoryType(mainCategoryCode);
        log.info("subCategory = " + subCategory);
        return subCategory;
    }

    @GetMapping("project1")
    public String getProjectTerms(@RequestParam(required = false) Integer no, Model model, Principal principal){
        if(no != null){
            return "redirect:/seller/regist/project2?no="+no;
        }else {
//            no = registProjectService.selectTemporaryProjectNoByUserId(principal.getName());
            no = projectService.selectTemporaryProjectNoByUserId("admin01");
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
    public String postProjectTerms(@RequestParam boolean check1, @RequestParam boolean check2,
                                   @RequestParam(required = false) Integer no,
                                   Principal principal) throws AccessAuthorityException {

        if(!(check1 && check2)) return "fail";
        if(no == null) {
            no = projectService.selectTemporaryProjectNoByUserId("admin01");
        }
        if(no != null) return "success";

        MemberDTO member = new MemberDTO();
//            member.setNo(registProjectService.selectMemberNoById(principal.getName()));

        int memberNo = registProjectService.selectMemberNoById("admin01");

        member.setNo(memberNo);

        ProjectDTO tempProject;
        tempProject = ProjectDTO.builder()
                .member(member)
                .build();
        projectService.insertTemporaryProject(tempProject);

        projectProgressService.insertProjectProgressStatus(
                ProjectProgressDTO.builder()
                        .content("")
                        .status(ProjectProgressStatus.TEMPORARY)
                        .refProjectNo(tempProject.getNo())
                        .build());

        return "success";
    }

    @GetMapping("project2")
    public String getProjectInfo(@RequestParam(required = false) Integer no, Model model, Principal principal) throws AccessAuthorityException {

        System.out.println("principal = " + principal);

        List<ProjectCategoryDTO> categoryList = projectCategoryService.selectByCategoryType(null);

        if(no == null){
//            no = registProjectService.selectTemporaryProjectNoByUserId(principal.getName());
            no = projectService.selectTemporaryProjectNoByUserId("admin01");
        }

        ProjectDTO project = null;

        if(no != null) {
//            ProjectDTO project = registProjectService.selectProjectInfoByProjectNo(no,principal.getName());
            project = projectService.selectProjectInfoByProjectNo(no,"admin01");
        }

        if(project == null ) throw new AccessAuthorityException("접근 권한이 없습니다.");
        
        model.addAttribute("project", project);
        model.addAttribute("mainCategory",categoryList);

        return "/seller/regist/registProject2";
    }

    @PostMapping(value = "project2")
    @ResponseBody
    public String postProjectIRegist(@RequestBody ProjectDTO project, Integer no, Principal principal){

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

        if(no == null){
            no = projectService.selectTemporaryProjectNoByUserId("admin01");
            project.setProjectProgress(ProjectProgressDTO.builder().status(ProjectProgressStatus.TEMPORARY).build());
        }
        project.setNo(no);

        projectService.updateProjectInfo(project);

        return "success";
    }

    @GetMapping("project3")
    public String getProjectProductRegist(@RequestParam(required = false) Integer no, Model model, Principal principal) throws AccessAuthorityException {

        boolean existProject = false;

        if(no == null){
//            no = registProjectService.selectTemporaryProjectNoByUserId(principal.getName());
            no = projectService.selectTemporaryProjectNoByUserId("admin01");
        }

        if(no != null) {
//            project = registProjectService.selectProjectByProjectNo(no.get(), principal.getName());
            existProject = projectService.existProjectByProjectNo(no, "admin01") ;
        }

        if (!existProject) throw new AccessAuthorityException("수정할 프로젝트가 존재하지 않습니다.");


        return "/seller/regist/registProject3";
    }
    @GetMapping("project3ProductGetdata")
    @ResponseBody
    public List<ProductDTO> getProjectProductData(@RequestParam(required = false) Integer no){
        if(no == null){
            no = projectService.selectTemporaryProjectNoByUserId("admin01");
        }
        List<ProductDTO> productList = new ArrayList<>();
        productList = productService.selectProductListByProjectNo(no,null);
        if(productList.size() == 0){
            productList.add(ProductDTO.builder().no(0).name("").introduction("").size("").build());
        }
        return productList;
    }
    @GetMapping("project3ProductGetImg")
    @ResponseBody
    public List<ProjectFileDTO> getProjectImg(@RequestParam(required = false) Integer no){
        if(no == null){
            no = projectService.selectTemporaryProjectNoByUserId("admin01");
        }
        System.out.println("no = " + no);
        List<ProjectFileDTO> templist = new ArrayList<>();

        if(no == null){
            templist.add(ProjectFileDTO.builder().build());
            return templist;
        }else {
            templist = projectFileService.selectProjectFileListByProjectNo(no,null);
            System.out.println("templist = " + templist);
            return templist;
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
            no = projectService.selectTemporaryProjectNoByUserId("admin01");
        }
        if(no == null) return "fail";

//        productList.get("oldImg").stream().forEach(e->{
//            System.out.println("e = " + e);
//        });

        List<ProductDTO> deleteProductList = productList.get("old")
                .stream().filter(oldE-> productList.get("new")
                        .stream().noneMatch(newE -> oldE.getNo() == newE.getNo()) )
                .collect(Collectors.toList());

        if(deleteProductList.size() != 0) {
            productService.deleteProjectProduct(deleteProductList);
        }

        productService.insertProjectProduct(productList.get("new"), no);



        try {
            if(!presentFile.isEmpty()) imageFileController.registFile(ProjectFileType.REPRESENT, presentFile, no);
            if(!thumbnailFile.isEmpty()) imageFileController.registFile(ProjectFileType.THUMBNAIL, thumbnailFile, no);

            if(fileList != null){
                for (MultipartFile file : fileList) {
                    imageFileController.registFile(ProjectFileType.CONTENT, file, no);
                }
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
            no = projectService.selectTemporaryProjectNoByUserId("admin01");
        }

        ProjectDTO project = null;
        if(no != null){
//        ProjectDTO project = registProjectService.selectProjectInfoByProjectNo(no,principal.getName());
        project = projectService.selectProjectInfoByProjectNo(no,null);
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
            no = projectService.selectTemporaryProjectNoByUserId("admin01");
        }

        if(no == null) return "fail";

        projectService.updateProjectContent(no, project);
        return "success";
    }

    @GetMapping("project5")
    public String getProjectFAQ(@RequestParam(required = false) Integer no, Principal principal) throws AccessAuthorityException {
        if(no == null){
//            no = registProjectService.selectTemporaryProjectNoByUserId(principal.getName());
            no = projectService.selectTemporaryProjectNoByUserId("admin01");
        }
        boolean existProject = false;

        if(no != null){
//            project = registProjectService.selectProjectByProjectNo(no,principal.getName());
            existProject = projectService.existProjectByProjectNo(no,"admin01");
        }

        if(!existProject) throw new AccessAuthorityException("수정가는 한 프로젝트가 존재하지 않습니다.");

        return "/seller/regist/registProject5";
    }
    @GetMapping("project5GetData")
    @ResponseBody
    public List<ProjectFAQDTO> getProjectFaqData(@RequestParam(required = false) Integer no, Principal principal){

        if(no == null){
//            no = registProjectService.selectTemporaryProjectNoByUserId(principal.getName());
            no = projectService.selectTemporaryProjectNoByUserId("admin01");
        }
        if(no == null){
            List<ProjectFAQDTO> tempProjectFaqList = new ArrayList<>();
            tempProjectFaqList.add(ProjectFAQDTO.builder().no(0).content("").title("").build());
            return tempProjectFaqList;
        }
        return projectFAQService.selectProjectFaqByProjectNo(no,null);
    }


    @PostMapping("project5")
    @ResponseBody
    public String postProjectFAQ(@RequestBody Map<String,List<ProjectFAQDTO>> projectFAQList,
                                 @RequestParam(required = false) Integer no, Principal principal){
        if(no == null){
//            no = registProjectService.selectTemporaryProjectNoByUserId(principal.getName());
            no = projectService.selectTemporaryProjectNoByUserId("admin01");
        }

        if(no == null) return "fail";

        for (ProjectFAQDTO e : projectFAQList.get("new")) {
            if (e.getNo() == 0) {
                projectFAQService.insertProjectFAQ(no, e);
            } else {
                projectFAQService.updateProjectFAQ(e);
            }
        }

        List<ProjectFAQDTO> deleteProjectFaq = projectFAQList.get("old")
                .stream().filter(oldE-> projectFAQList.get("new")
                        .stream().noneMatch(newE -> oldE.getNo() == newE.getNo()) )
                .collect(Collectors.toList());
        log.info("deleteProjectFaq = " + deleteProjectFaq);

        if(deleteProjectFaq.size() != 0 ) projectFAQService.deleteProjectFAQ(deleteProjectFaq);

        log.info("projectFAQList = " + projectFAQList);


        return "success";
    }


    @GetMapping("project6")
    public String getProjectNEWS(@RequestParam(required = false) Integer no,
                                 Model model, Principal principal) throws AccessAuthorityException {
        if(no == null){
//            no = registProjectService.selectTemporaryProjectNoByUserId(principal.getName());
            no = projectService.selectTemporaryProjectNoByUserId("admin01");
        }

        boolean existProject = false;

        if(no != null){
//            existProject = registProjectService.existProjectByProjectNo(no, principal.getName());
            existProject = projectService.existProjectByProjectNo(no, "admin01");
        }

        if(!existProject) throw new AccessAuthorityException("등록 및 수정 가능한 프로젝트가 없습니다");

        return "/seller/regist/registProject6";
    }

    @GetMapping("project6GetData")
    @ResponseBody
    public List<ProjectNewsDTO> getProjectNEWSData(@RequestParam(required = false) Integer no,Principal principal){

        if(no == null){
//            no = registProjectService.selectTemporaryProjectNoByUserId(principal.getName());
            no = projectService.selectTemporaryProjectNoByUserId("admin01");
        }

        List<ProjectNewsDTO> projectNewsList= projectNewsService.selectProjectNewsListByProjectNo(no,null);

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
        return projectNewsService.selectProjectNewsByProjectNewsNo(no,null);
    }

    @GetMapping("deleteProjectNews")
    @ResponseBody
    public String deletePojectNews(Integer no){

        int result = projectNewsService.deleteProjectNews(no);

        if(result > 0) return "success";

        return "fail";
    }

    @PostMapping("modifyProjectNews")
    @ResponseBody
    public String modifyPojectNews(@RequestBody  ProjectNewsDTO projectNews){
        int result = projectNewsService.updateProjectNews(projectNews);

        if(result > 0) return "success";

        return "fail";

    }


    @PostMapping("project6")
    @ResponseBody
    public String postProjectNEWS(@RequestBody ProjectNewsDTO projectNewsDTO, Integer no, Principal principal){
        System.out.println("principal = " + principal);
        if(no == null){
//            no = registProjectService.selectTemporaryProjectNoByUserId(principal.getName());
            no = projectService.selectTemporaryProjectNoByUserId("admin01");
        }

        int result = projectNewsService.insertProjectNews(no, projectNewsDTO);

        if(result > 0) return "success";

        return "fail";
    }

    @GetMapping("project7")
    public String getProjectInfo(@RequestParam(required = false) Integer no,
                                 Model model){
        if(no == null){
//            no = registProjectService.selectTemporaryProjectNoByUserId(principal.getName());
            no = projectService.selectTemporaryProjectNoByUserId("admin01");
        }


        ProjectDTO project = projectService.selectProjectByProjectNo(no,null);

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
    public String projectRegist(@RequestParam(required = false) Integer no){
        System.out.println("no = " + no);

        if(no==null){
            no = projectService.selectTemporaryProjectNoByUserId("admin01");
        }

        ProjectProgressDTO currentProjectProgress = projectProgressService.progressLastStatusById(no,null);
        System.out.println("currentProjectProgress = " + currentProjectProgress);
        if(!currentProjectProgress.getStatus().equals(ProjectProgressStatus.TEMPORARY)) return "success";

        ProjectProgressDTO projectProgress;
        projectProgress = ProjectProgressDTO.builder()
                .refProjectNo(no)
                .status(ProjectProgressStatus.SCREENING).content("심사요청").build();

        projectProgressService.insertProjectProgressStatus(projectProgress);

        return "success";
    }



}
