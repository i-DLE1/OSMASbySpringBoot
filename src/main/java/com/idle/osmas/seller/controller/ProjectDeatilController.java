package com.idle.osmas.seller.controller;

import com.idle.osmas.common.exception.AccessAuthorityException;
import com.idle.osmas.seller.dto.*;
import com.idle.osmas.seller.service.ProjectProgressServiceImpl;
import com.idle.osmas.seller.service.SellerPageServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/seller/projectDetail/*")
public class ProjectDeatilController {
    private final SellerPageServiceImpl sellerPageService;
    private final ProjectProgressServiceImpl projectProgressService;


    public ProjectDeatilController(SellerPageServiceImpl sellerPageService, ProjectProgressServiceImpl projectProgressService) {
        this.sellerPageService = sellerPageService;
        this.projectProgressService = projectProgressService;
    }

    @GetMapping("projectDetail")
    public String projectDetail(@RequestParam String id){
        return "/seller/popup/projectDetail";
    }

    @GetMapping("cancel")
    public String getCacnel(@RequestParam int no, Model model){
        ProjectDTO project = sellerPageService.selectByProjectId(no);

        System.out.println("project = " + project);

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
    public String getQaAnswer(@RequestParam String id, Model model) {

        System.out.println("id = " + id);

        ProjectQnADTO projectQnA = sellerPageService.selectByQnANo(Integer.valueOf(id));

        System.out.println("projectQnAAnswer = " + projectQnA);

        model.addAttribute("projectQnA", projectQnA);
        return "/seller/popup/qa_answer";
    }
    @PostMapping("qaAnswer")
    @ResponseBody
    public String postQaAswer(@RequestBody String content,
                              @RequestParam Integer id,
                              @RequestParam String submitType,
                              Model model){
        String result = "";

        Map<String, Object> qnaData = new HashMap<>();
        qnaData.put("no", id);
        qnaData.put("content", content);
        qnaData.put("memberId","admin01");
        if("regist".equals(submitType)){
            sellerPageService.insertProjectQnAAnswer(qnaData);
            result = "success";
        }else if("modify".equals(submitType)){
            sellerPageService.updateProjectQnAAnswer(qnaData);
            System.out.println("수정 성공");
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
    public String retry(@RequestParam int no, Model model) throws AccessAuthorityException {

        ProjectProgressDTO projectProgress = projectProgressService.progressLastStatusById(no, ProjectProgressStatus.REJECTED);

        if (projectProgress == null) throw new AccessAuthorityException("현재 프로젝트는 재심사 대상이 아닙니다.");

        model.addAttribute("projectProgress", projectProgress);
        return "/seller/popup/retry";

    }

    @PostMapping("retry")
    @ResponseBody
    public String postRetry(@RequestBody String content, @RequestParam int id){

        ProjectProgressDTO projectProgress = new ProjectProgressDTO();
        projectProgress = ProjectProgressDTO.builder()
                .refProjectNo(id)
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
