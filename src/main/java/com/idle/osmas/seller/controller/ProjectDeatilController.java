package com.idle.osmas.seller.controller;

import com.idle.osmas.common.exception.seller.ProjectRetryException;
import com.idle.osmas.seller.dto.*;
import com.idle.osmas.seller.service.ProjectProgressService;
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
    public String getCacnel(@RequestParam int id, Model model){
        ProjectDTO project = sellerPageService.selectByProjectId(id);

        System.out.println("project = " + project);

        String targetRate = String.valueOf(project.getCurrentAmount() / project.getTargetAmount()  * 100)+"%";
        model.addAttribute("project",project);
        model.addAttribute("targetRate",targetRate) ;
        return "/seller/popup/cancel";
    }

    @PostMapping("cancel")
    @ResponseBody
    public String postCancel(@RequestParam Integer id, @RequestBody String content){

        System.out.println("id = " + id);
        System.out.println("content = " + content);

        // 전달 완료 insert문 추가 예정

        // find userId
        ProjectProgressDTO projectProgress = new ProjectProgressDTO();
        projectProgress = ProjectProgressDTO.builder()
                                            .status(ProjectProgressStatus.CANCEL)
                                            .content(content)
                                            .refProjectNo(id)
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
    public String refuse(@RequestParam String id){
        return "/seller/popup/refuse";
    }

    @GetMapping("retry")
    public String retry(@RequestParam int id, Model model) throws ProjectRetryException {

        ProjectProgressDTO projectProgress = projectProgressService.progressLastStatusById(id, ProjectProgressStatus.REJECTED);

        try {
            if (!projectProgress.getContent().isEmpty()) {
                model.addAttribute("projectProgress", projectProgress);
            }
            return "/seller/popup/retry";
        } catch (NullPointerException e) {
            throw new ProjectRetryException("선택하신 프로젝트는 재심사 대상이 아닙니다.");
        }
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
