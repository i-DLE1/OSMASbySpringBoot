package com.idle.osmas.seller.controller;

import com.idle.osmas.admin.dto.TermsDTO;
import com.idle.osmas.common.exception.AccessAuthorityException;
import com.idle.osmas.member.dto.UserImpl;
import com.idle.osmas.seller.dto.*;
import com.idle.osmas.seller.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/seller/regist/*")
public class RegistProjectController {

    private final ProjectService projectService;

    private final ProjectProgressService projectProgressService;

    private final ProductService productService;

    private final ProjectFileService projectFileService;

    private final ProjectCategoryService projectCategoryService;
    private final ProjectFAQService projectFAQService;

    private final ProjectNewsService projectNewsService;

    private final ProjectTermService termsService;

    private final ImageFileController imageFileController;

    private final Logger log = LoggerFactory.getLogger(this.getClass());



    public RegistProjectController(ProjectService projectService,
                                   ProjectProgressService projectProgressService,
                                   ProductService productService,
                                   ProjectFileService projectFileService,
                                   ProjectCategoryService projectCategoryService,
                                   ProjectFAQService projectFAQService,
                                   ProjectNewsService projectNewsService,
                                   ImageFileController imageFileController,
                                   ProjectTermService termsService) {

        this.projectService = projectService;
        this.projectProgressService = projectProgressService;
        this.productService = productService;
        this.projectFileService = projectFileService;
        this.projectCategoryService = projectCategoryService;
        this.projectFAQService = projectFAQService;
        this.projectNewsService = projectNewsService;
        this.imageFileController = imageFileController;
        this.termsService = termsService;
    }

    public Map<String, String> submitButtonNaming(int ProjectNo, String temp, String notTemp){
        Map<String, String> submitName = new HashMap<>();

        ProjectProgressDTO projectProgress = projectProgressService.progressLastStatusById(ProjectNo, null);
        if(projectProgress.getStatus().equals(ProjectProgressStatus.TEMPORARY)){
            submitName.put("submitName",temp);
        }else {
            submitName.put("submitName", notTemp);
        }

        return submitName;
    }

    @GetMapping("getSubCategory")
    @ResponseBody
    public List<ProjectCategoryDTO> getSubCategory(@RequestParam Integer mainCategoryCode){

        List<ProjectCategoryDTO> subCategory = projectCategoryService.selectByCategoryType(mainCategoryCode);

        return subCategory;
    }

    @GetMapping("tempProjectConfirm")
    @ResponseBody
    public Map<String, Object> tempProjectConfirm(Principal principal){

        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        Integer no = projectService.selectTemporaryProjectNoByUserId(user.getNo());

        Map<String, Object> result = new HashMap<>();

        if(no != null){
            result.put("no", no);
            result.put("result","isExist");
            return result;
        }
            result.put("result","notExist");

        return result;
    }

    @GetMapping("deleteTempProject")
    @ResponseBody
    public String deleteTempProject(Principal principal){
        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        Integer no = projectService.selectTemporaryProjectNoByUserId(user.getNo());

        int result = projectService.deleteProjectByProjectNo(no);

        if(result == 1) return "success";

        return "fail";
    }

    @GetMapping("project1")
    public String getProjectTerms(@RequestParam(required = false) Integer no, Model model, Principal principal) throws AccessAuthorityException {

        if(principal == null) throw new AccessAuthorityException("접속 권한이 없습니다.");

        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        if(no == null){
            no = projectService.selectTemporaryProjectNoByUserId(user.getNo());
        }

        if(no != null) return "redirect:/seller/regist/project2?no="+no;


        List<TermsDTO> termsList = termsService.selectTermListByNo();

        String term1 = termsList.get(0).getContent();
        String term2 = termsList.get(1).getContent();

        model.addAttribute("term1", term1);
        model.addAttribute("term2", term2);

        if(no != null){
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

        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        if(!(check1 && check2)) return "fail";

        if(no == null) {
            no = projectService.selectTemporaryProjectNoByUserId(user.getNo());

        }

        if(no != null) return "success";

        ProjectDTO tempProject;
        tempProject = ProjectDTO.builder()
                .refMemberNo(user.getNo())
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
    public String getProjectInfo(@RequestParam(required = false) Integer no,
                                 Model model, Principal principal) throws AccessAuthorityException {


        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        List<ProjectCategoryDTO> categoryList = projectCategoryService.selectByCategoryType(null);

        if(no == null){
            no = projectService.selectTemporaryProjectNoByUserId(user.getNo());
        }

        if(no == null ) throw new AccessAuthorityException("접근 권한이 없습니다.");

        ProjectDTO project = null;

        if(no != null) {
            project = projectService.selectProjectInfoByProjectNo(no, user.getNo());
        }

        if(project == null ) throw new AccessAuthorityException("접근 권한이 없습니다.");
        
        model.addAttribute("project", project);
        model.addAttribute("mainCategory",categoryList);
        model.mergeAttributes(submitButtonNaming(no,"임시저장","수정"));

        return "/seller/regist/registProject2";
    }

    @PostMapping(value = "project2")
    @ResponseBody
    public String postProjectRegist(@RequestBody ProjectDTO project, Integer no, Principal principal){


        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
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
            no = projectService.selectTemporaryProjectNoByUserId(user.getNo());
            project.setProjectProgress(ProjectProgressDTO.builder().status(ProjectProgressStatus.TEMPORARY).build());
        }
        project.setNo(no);

        projectService.updateProjectInfo(project);

        return "success";
    }

    @GetMapping("project3")
    public String getProjectProductRegist(@RequestParam(required = false) Integer no,
                                          Principal principal, Model model) throws AccessAuthorityException {


        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        boolean existProject = false;

        if(no == null){
            no = projectService.selectTemporaryProjectNoByUserId(user.getNo());
        }

        if(no != null) {
            existProject = projectService.existProjectByProjectNo(no, user.getNo()) ;
        }

        if (!existProject) throw new AccessAuthorityException("수정할 프로젝트가 존재하지 않습니다.");

        model.mergeAttributes(submitButtonNaming(no,"임시저장","수정"));

        return "/seller/regist/registProject3";
    }

    @GetMapping("project3ProductGetdata")
    @ResponseBody
    public List<ProductDTO> getProjectProductData(@RequestParam(required = false) Integer no,
                                                  Principal principal) throws AccessAuthorityException {

        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        if(no == null){
            no = projectService.selectTemporaryProjectNoByUserId(user.getNo());
        }
        if(no == null) throw new AccessAuthorityException("접근할 권한이 없습니다.");

        List<ProductDTO> productList;

        productList = productService.selectProductListByProjectNo(no,user.getNo());

        if(productList.size() == 0){
            productList.add(ProductDTO.builder().no(0).name("").introduction("").size("").build());
        }

        return productList;
    }
    @GetMapping("project3ProductGetImg")
    @ResponseBody
    public List<ProjectFileDTO> getProjectImg(@RequestParam(required = false) Integer no, Principal principal){

        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        if(no == null){
            no = projectService.selectTemporaryProjectNoByUserId(user.getNo());
        }

        List<ProjectFileDTO> templist = new ArrayList<>();

        if(no == null){
            templist.add(ProjectFileDTO.builder().build());
            return templist;
        }else {
            templist = projectFileService.selectProjectFileListByProjectNo(no,user.getNo());
            return templist;
        }
    }

    @PostMapping(value = "project3")
    @ResponseBody
    public String postProjectProductRegist(@RequestPart Map<String, List<ProductDTO>> productList,
                                           @RequestPart List<ProjectFileDTO> projectFileList,
                                           @RequestPart(required = false) MultipartFile presentFile,
                                           @RequestPart(required = false) MultipartFile thumbnailFile,
                                           @RequestPart(value = "file-0", required = false) MultipartFile file0,
                                           @RequestPart(value = "file-1", required = false) MultipartFile file1,
                                           @RequestPart(value = "file-2", required = false) MultipartFile file2,
                                           @RequestParam(required = false) Integer no, Principal principal) {

        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        if(no == null) {
            no = projectService.selectTemporaryProjectNoByUserId(user.getNo());
        }
        if(no == null) return "fail";

        Map<String, String> oldProjectFile = new HashMap<>();

        projectFileList.stream().forEach(e->{
            String suffix = e.getChangeName().substring(0,e.getChangeName().indexOf("_"));
            oldProjectFile.put(suffix, e.getChangeName());
        });

        List<ProductDTO> deleteProductList = productList.get("old")
                .stream().filter( oldE-> productList.get("new")
                        .stream().noneMatch( newE -> oldE.getNo() == newE.getNo()) )
                .collect(Collectors.toList());

        if(deleteProductList.size() != 0) {
            productService.deleteProjectProduct(deleteProductList);
        }

        Integer projectNo = no;
        List<ProductDTO> insertProductList = productList.get("new")
                .stream().filter(e-> e.getNo() == 0).map(e->{
                    e.setRefProjectNo(projectNo);
                    return e;
                }).collect(Collectors.toList());

        List<ProductDTO> updateProductList = productList.get("new")
                .stream().filter(e-> e.getNo() != 0).collect(Collectors.toList());

        productService.insertProjectProduct(insertProductList);
        productService.updateProjectProduct(updateProductList);


        if(presentFile != null) {
            imageFileController.deleteFile("project",no,oldProjectFile.get("represent"));

            imageFileController.saveFile(ProjectFileType.REPRESENT, presentFile, no);
        }

        if(thumbnailFile != null) {
            imageFileController.deleteFile("project",no,oldProjectFile.get("thumbnail"));
            imageFileController.saveFile(ProjectFileType.THUMBNAIL, thumbnailFile, no);
        }

        if(file0 != null){
            imageFileController.deleteFile("project",no,oldProjectFile.get("content0"));
            imageFileController.saveFile(ProjectFileType.CONTENT, file0, no, "CONTENT0");
        }

        if(file1 != null){
            imageFileController.deleteFile("project",no,oldProjectFile.get("content1"));
            imageFileController.saveFile(ProjectFileType.CONTENT, file1, no, "CONTENT1");
        }

        if(file2 != null){
            imageFileController.deleteFile("project",no,oldProjectFile.get("content2"));
            imageFileController.saveFile(ProjectFileType.CONTENT, file2, no, "CONTENT2");
        }


        return "success";
    }

    @GetMapping("project4")
    public String getProjectProductDetail(@RequestParam(required = false) Integer no, Model model,Principal principal) throws AccessAuthorityException {

        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        if(no == null){
            no = projectService.selectTemporaryProjectNoByUserId(user.getNo());
        }

        ProjectDTO project = null;

        if(no != null){
            project = projectService.selectProjectInfoByProjectNo(no,null);
        }

        if (project == null) throw new AccessAuthorityException("등록중인 프로젝트가 없습니다.");

        model.addAttribute("project" ,project); // 웹에디터 기본값
        model.mergeAttributes(submitButtonNaming(no,"임시저장","수정"));

        return "/seller/regist/registProject4";
    }
    
    @PostMapping("project4")
    @ResponseBody
    public String postProjectProductDetail(@RequestBody ProjectDTO project,
                                           @RequestParam(required = false) Integer no, Principal principal){

        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        if(no == null){
            no = projectService.selectTemporaryProjectNoByUserId(user.getNo());
        }

        if(no == null) return "fail";

        projectService.updateProjectContent(no, project);

        return "success";
    }

    @GetMapping("project5")
    public String getProjectFAQ(@RequestParam(required = false) Integer no,
                                Principal principal, Model model) throws AccessAuthorityException {

        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        if(no == null){
            no = projectService.selectTemporaryProjectNoByUserId(user.getNo());
        }

        boolean existProject = false;

        if(no != null){
            existProject = projectService.existProjectByProjectNo(no, user.getNo());
        }

        if(!existProject) throw new AccessAuthorityException("수정가는 한 프로젝트가 존재하지 않습니다.");

        model.mergeAttributes(submitButtonNaming(no,"임시저장","수정"));

        return "/seller/regist/registProject5";
    }
    @GetMapping("project5GetData")
    @ResponseBody
    public List<ProjectFAQDTO> getProjectFaqData(@RequestParam(required = false) Integer no, Principal principal) throws AccessAuthorityException {
        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        if(no == null){
            no = projectService.selectTemporaryProjectNoByUserId(user.getNo());
        }

        if(no == null) throw new AccessAuthorityException("접근할 권한이 없습니다.");

        List<ProjectFAQDTO> projectFAQList =  projectFAQService.selectProjectFaqByProjectNo(no, user.getNo());

        if(projectFAQList.size() == 0 ){
            projectFAQList.add(ProjectFAQDTO.builder().no(0).content("").title("").build());
        }

        return projectFAQList;
    }


    @PostMapping("project5")
    @ResponseBody
    public String postProjectFAQ(@RequestBody Map<String,List<ProjectFAQDTO>> projectFAQList,
                                 @RequestParam(required = false) Integer no, Principal principal){

        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        if(no == null){
            no = projectService.selectTemporaryProjectNoByUserId(user.getNo());
        }

        if(no == null) return "fail";

        Integer finalNo = no;

        List<ProjectFAQDTO> insertProjectFaqList = projectFAQList.get("new")
                .stream().filter(e->e.getNo() == 0).map(e->{
                    e.setProjectNo(finalNo);
                    return e;
                }).collect(Collectors.toList());

        List<ProjectFAQDTO> updateProjectFaqList = projectFAQList.get("new")
                .stream().filter(e->e.getNo() != 0).map(e->{
                    e.setProjectNo(finalNo);
                    return e;
                }).collect(Collectors.toList());


        projectFAQService.insertProjectFAQ(insertProjectFaqList);
        projectFAQService.updateProjectFAQ(updateProjectFaqList);

        List<ProjectFAQDTO> deleteProjectFaq = projectFAQList.get("old")
                .stream().filter(oldE-> projectFAQList.get("new")
                        .stream().noneMatch(newE -> oldE.getNo() == newE.getNo()) )
                .collect(Collectors.toList());

        if(deleteProjectFaq.size() != 0 ) projectFAQService.deleteProjectFAQ(deleteProjectFaq);

        return "success";
    }

    @GetMapping("project6")
    public String getProjectNEWS(@RequestParam(required = false) Integer no,
                                 Model model, Principal principal) throws AccessAuthorityException {

        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        if(no == null){
            no = projectService.selectTemporaryProjectNoByUserId(user.getNo());
        }

        boolean existProject = false;

        if(no != null){
            existProject = projectService.existProjectByProjectNo(no, user.getNo());
        }

        if(!existProject) throw new AccessAuthorityException("등록 및 수정 가능한 프로젝트가 없습니다");

        model.mergeAttributes(submitButtonNaming(no,"임시저장","수정"));
        return "/seller/regist/registProject6";
    }

    @GetMapping("project6GetData")
    @ResponseBody
    public List<ProjectNewsDTO> getProjectNEWSData(@RequestParam(required = false) Integer no,
                                                   Principal principal){

        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        if(no == null){
            no = projectService.selectTemporaryProjectNoByUserId(user.getNo());
        }

        List<ProjectNewsDTO> projectNewsList= projectNewsService.selectProjectNewsListByProjectNo(no,null);

        if(projectNewsList.size() == 0) return projectNewsList;

        projectNewsList.stream().map(e->{
            String afterString = e.getContent()
                    .replaceAll("<([^>]+)>", "")
                    .replaceAll("&nbsp;", "");
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
    public String postProjectNEWS(@RequestBody ProjectNewsDTO projectNewsDTO,
                                  Integer no, Principal principal) throws AccessAuthorityException {

        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        if(no == null){
            no = projectService.selectTemporaryProjectNoByUserId(user.getNo());
        }

        if(no == null) throw new AccessAuthorityException("접근 권한이 없습니다.");
        int result = projectNewsService.insertProjectNews(no, projectNewsDTO);

        if(result > 0) return "success";

        return "fail";
    }

    @GetMapping("project7")
    public String getProjectInfo(@RequestParam(required = false) Integer no,
                                 Principal principal, Model model) throws AccessAuthorityException {

        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        if(no == null){
            no = projectService.selectTemporaryProjectNoByUserId(user.getNo());
        }

        if(no == null) throw new AccessAuthorityException("접근 권한이 없습니다");

        ProjectDTO project = projectService.selectProjectByProjectNo(no,user.getNo());

        String afterString = project.getContent();

        if(afterString != null){
            afterString = project.getContent()
                    .replaceAll("<([^>]+)>",  "")
                    .replaceAll("&nbsp;", "");
        }

        project.setContent(afterString);

        model.addAttribute("projectInfo",project);
        model.mergeAttributes(submitButtonNaming(no,"심사요청","수정"));

        return "/seller/regist/registProject7";
    }

    @GetMapping("projectRegist")
    @ResponseBody
    public String projectRegist(@RequestParam(required = false) Integer no, Principal principal){

        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        if(no == null){
            no = projectService.selectTemporaryProjectNoByUserId(user.getNo());
        }

        ProjectProgressDTO currentProjectProgress = projectProgressService.progressLastStatusById(no,null);

        if(!currentProjectProgress.getStatus().equals(ProjectProgressStatus.TEMPORARY)) return "success";

        ProjectProgressDTO projectProgress;
        projectProgress = ProjectProgressDTO.builder()
                .refProjectNo(no)
                .status(ProjectProgressStatus.SCREENING).content("심사요청").build();

        projectProgressService.insertProjectProgressStatus(projectProgress);

        return "success";
    }

}
