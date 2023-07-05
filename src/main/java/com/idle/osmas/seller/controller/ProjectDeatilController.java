package com.idle.osmas.seller.controller;

import com.idle.osmas.common.exception.AccessAuthorityException;
import com.idle.osmas.member.dto.UserImpl;
import com.idle.osmas.seller.dto.*;
import com.idle.osmas.seller.service.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.channels.FileChannel;
import java.security.Principal;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/projectDetail/*")
public class ProjectDeatilController {
    private final ProjectProgressServiceImpl projectProgressService;

    private final ProductService productService;

    private final ProjectService projectService;

    private final ProjectQnAService projectQnAService;

    public ProjectDeatilController(ProjectProgressServiceImpl projectProgressService, ProductService productService, ProjectService projectService, ProjectQnAService projectQnAService) {
        this.projectProgressService = projectProgressService;
        this.productService = productService;
        this.projectService = projectService;
        this.projectQnAService = projectQnAService;
    }

    @GetMapping("projectDetail")
    public String projectDetail(@RequestParam int no, Principal principal, Model model) {
        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        ProjectDTO project = projectService.selectProjectByProjectNo(no,user.getNo());

        List<ProductDTO> productList = productService.selectProductListByProjectNo(no,user.getNo());

        Period betweenDays = Period.between(LocalDate.now(), project.getEndDate());

        model.addAttribute("days",betweenDays.getDays());
        model.addAttribute("project",project);
        model.addAttribute("productList",productList);

        return "/seller/popup/projectDetail";
    }

    @GetMapping("cancel")
    public String getCacnel(@RequestParam int no,  Principal principal, Model model){

        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        ProjectDTO project = projectService.selectProjectCancelInfoByProjectId(no, user.getNo());

        String targetRate = String.valueOf(project.getCurrentAmount() / project.getTargetAmount()  * 100)+"%";

        model.addAttribute("project",project);
        model.addAttribute("targetRate",targetRate) ;
        return "/seller/popup/cancel";
    }

    @PostMapping("cancel")
    @ResponseBody
    public String postCancel(@RequestParam Integer no, @RequestBody String content){

        ProjectProgressDTO projectProgress;
        projectProgress = ProjectProgressDTO.builder()
                                            .status(ProjectProgressStatus.CANCEL)
                                            .content(content)
                                            .refProjectNo(no)
                                            .build();

        System.out.println("projectProgress = " + projectProgress);

        int insertResult = projectProgressService.insertProjectProgressStatus(projectProgress);

        if(insertResult > 0){
            return "success";
        }else {
            return "fail";
        }
    }

    @GetMapping("qaAnswer")
    public String getQaAnswer(@RequestParam Integer no, Principal principal, Model model) {

        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        ProjectQnADTO projectQnA = projectQnAService.selectByQnANo(Integer.valueOf(no));

        model.addAttribute("projectQnA", projectQnA);
        return "/seller/popup/qa_answer";
    }
    @PostMapping("qaAnswer")
    @ResponseBody
    public String postQaAswer(@RequestBody String content,
                              @RequestParam Integer no,
                              @RequestParam String submitType,
                              Principal principal){
        String result = "";

        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        Map<String, Object> qnaData = new HashMap<>();

        qnaData.put("no", no);
        qnaData.put("content", content);
        qnaData.put("userNo",user.getNo());

        if("regist".equals(submitType)){
            projectQnAService.insertProjectQnAAnswer(qnaData);
            result = "success";
        }else if("modify".equals(submitType)){
            projectQnAService.updateProjectQnAAnswer(qnaData);
            result = "success";
        }else {
            result = "fail";
        }

        return result;
    }


    @GetMapping("refuse")
    public String refuse(@RequestParam String no){
        return "/seller/popup/refuse";
    }

    @GetMapping("retry")
    public String retry(@RequestParam int no, Principal principal, Model model) throws AccessAuthorityException {

        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        ProjectProgressDTO projectProgress = projectProgressService.progressLastStatusById(no, ProjectProgressStatus.REJECTED);

        if (projectProgress == null) throw new AccessAuthorityException("현재 프로젝트는 재심사 대상이 아닙니다.");

        ProjectDTO projectDTO = projectService.selectProjectByProjectNo(no, user.getNo());
        int productCount = productService.selectProductListCountByProjectNo(no);

        model.addAttribute("project",projectDTO);
        model.addAttribute("productCount",productCount);
        model.addAttribute("projectProgress", projectProgress);

        return "/seller/popup/retry";

    }

    @PostMapping("retry")
    @ResponseBody
    public String postRetry(@RequestBody String content, @RequestParam int no, Principal principal){

        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        boolean existPorject = projectService.existProjectByProjectNo(no,user.getNo());

        if (!existPorject) return "니꺼 아님";

        ProjectProgressDTO projectProgress;
        projectProgress = ProjectProgressDTO.builder()
                .refProjectNo(no)
                .content(content)
                .status(ProjectProgressStatus.SCREENING)
                .build();

        int result = projectProgressService.insertProjectProgressStatus(projectProgress);

        if(result > 0){
            return "success";
        }else {
            return "fail";
        }
    }
}
