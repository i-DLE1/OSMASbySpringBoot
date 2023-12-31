package com.idle.osmas.seller.controller;

import com.idle.osmas.common.exception.AccessAuthorityException;
import com.idle.osmas.member.dto.MemberStatus;
import com.idle.osmas.member.dto.UserImpl;
import com.idle.osmas.seller.dto.*;
import com.idle.osmas.seller.service.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/seller")
public class SellerController {

    private final ImageFileController imageFileController;

    private final ProjectService projectService;

    private final ProjectFileService projectFileService;


    private final ProjectQnAService projectQnAService;


    private final ProjectProgressService projectProgressService;

    private final OrderListService orderListService;

    private int DEFAULT_MAX_ROWS = 10;

    public SellerController(ImageFileController imageFileController,
                            ProjectService projectService,
                            ProjectFileService projectFileService,
                            ProjectQnAService projectQnAService,
                            ProjectProgressService projectProgressService,
                            OrderListService orderListService) {

        this.imageFileController = imageFileController;
        this.projectService = projectService;
        this.projectFileService = projectFileService;
        this.projectQnAService = projectQnAService;
        this.projectProgressService = projectProgressService;
        this.orderListService = orderListService;
    }

    public String listType(Optional<String> listType){

        String resultListType = "";

        switch (listType.orElse("")){
            case "all" :
                resultListType = "전체조회";
                break;
            case "screening" :
                resultListType = "심사중";
                break;
            case "processing" :
                resultListType = "진행중";
                break;
            case "refuse" :
                resultListType = "반려";
                break;
            case "cancel" :
                resultListType = "취소";
                break;
            case "wait" :
                resultListType = "답변대기중";
                break;
            case "complete" :
                resultListType = "완료";
                break;
            case "receipt" :
                resultListType = "주문접수";
                break;
            case "delivery" :
                resultListType = "배송처리";
                break;
            case "refund" :
                resultListType = "교환환불";
                break;
            case "cancelList" :
                resultListType = "취소내역";
                break;
            case "calculate" :
                resultListType = "주문정보";
                break;
            default:
                resultListType = "전체조회";
        }

        return resultListType;
    }
    public Map<String, Integer> getPagenation(int currentPage, int maxPage){

        Map<String, Integer> getPageInfo = new HashMap<>();

        int minPage = 1;
        int startPageNum = 1;
        int endPageNum = maxPage;

        if(maxPage - 2 > currentPage){
            if(currentPage > 2) startPageNum  = currentPage - 2;
            if(endPageNum - 2 > currentPage) endPageNum = currentPage + 2;
        }

        getPageInfo.put("startPageNum",startPageNum);
        getPageInfo.put("endPageNum", endPageNum);
        getPageInfo.put("minPageNum", minPage);
        getPageInfo.put("maxPageNum", maxPage);
        getPageInfo.put("currentPageNum", currentPage);

        return getPageInfo;
    }

    @GetMapping("/projectList")
    public String getProjectList(@RequestParam(required = false) Optional<String> listType,
                                 @RequestParam(required = false) String search,
                                 @RequestParam(required = false) Integer pageNo,
                                 Principal principal, Model model) throws AccessAuthorityException {

        if(principal == null) throw new AccessAuthorityException("접근 권한이 없습니다.");

        if(listType.isEmpty()){
            return "redirect:/seller/projectList?listType=all";
        }
        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        System.out.println("user = " + user.getStatus());
        if(!user.getStatus().equals(MemberStatus.USE.toString())) {
            throw new AccessAuthorityException("접근 권한이 없습니다.");
        }

        if(pageNo == null ) pageNo = 1;

        Map<String, Object> searchCriteria = new HashMap<>();

        int startNo = 1 + ((pageNo - 1) * DEFAULT_MAX_ROWS);
        int endNo = ((pageNo) * DEFAULT_MAX_ROWS);

        searchCriteria.put("listType", listType.get().toString().toUpperCase());
        searchCriteria.put("search", search);
        searchCriteria.put("userNo", user.getNo());
        searchCriteria.put("startNo", startNo);
        searchCriteria.put("endNo", endNo);

        List<ProjectDTO> projectList = projectService.selectByProgressAndSearchProjectManagement(searchCriteria);

        int count = projectService.selectByProgressAndSearchProjectManagementCount(searchCriteria);
        int endRow = count - ((pageNo -1) * DEFAULT_MAX_ROWS) < 0 ? count % DEFAULT_MAX_ROWS : count -((pageNo - 1) * DEFAULT_MAX_ROWS);
        int maxPage = ((int) Math.floor((double) count / (double) DEFAULT_MAX_ROWS)) + (endRow % 10 == 0 ? 0 : 1);


        String defaultSearch = search == null ? "검색할 프로젝트 명을 입력하세요" : search;

        model.addAttribute("endRow", endRow);
        model.addAttribute("search", defaultSearch);
        model.addAttribute("listType", listType(listType));
        model.addAttribute("userName", user.getName()); // 사용자명
        model.addAttribute("projectList", projectList);
        model.mergeAttributes(getPagenation(pageNo,maxPage));


        return "/seller/sellerProjectList";
    }



    // miji 추가
    @GetMapping(value = {"/","/orderList"})
    public String getOrderList(@RequestParam(required = false) Optional<String> listType,
                               @RequestParam(required = false) String searchType,
                               @RequestParam(required = false) String search,
                               @RequestParam(required = false) Integer pageNo,
                               @RequestParam(defaultValue= "-1" ,required = false) int projectNo2,
                               Principal principal, Model model) throws AccessAuthorityException {

        if(principal == null) throw new AccessAuthorityException("접근 권한이 없습니다.");


        if(listType.isEmpty()){
            return "redirect:/seller/orderList?listType=all";
        }

        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        if(pageNo == null ) pageNo = 1;

        Map<String, Object> searchCriteria = new HashMap<>();

        int startNo = 1 + ((pageNo - 1) * DEFAULT_MAX_ROWS);
        int endNo = ((pageNo) * DEFAULT_MAX_ROWS);
        int projectNo = 0;

        searchCriteria.put("listType", listType.get().toString().toUpperCase());
        searchCriteria.put("searchType",searchType);
        searchCriteria.put("search", search);
        searchCriteria.put("userNo", user.getNo());
        searchCriteria.put("startNo", startNo);
        searchCriteria.put("endNo", endNo);
        searchCriteria.put("projectNo2", projectNo2);

//        List<ProjectDTO> projectList = projectService.selectByProgressAndSearchProjectManagement(searchCriteria);
        List<SponsoredPRJDTO> orderList = orderListService.selectOrderList(searchCriteria);
        List<SalesDTO> projectList = orderListService.selectProjectByUserNo(searchCriteria);

        int count = projectService.selectByProgressAndSearchProjectManagementCount(searchCriteria);
        int endRow = count - ((pageNo -1) * DEFAULT_MAX_ROWS) < 0 ? count % DEFAULT_MAX_ROWS : count -((pageNo - 1) * DEFAULT_MAX_ROWS);
        int maxPage = ((int) Math.floor((double) count / (double) DEFAULT_MAX_ROWS)) + 1;


        String defaultSearch = search == null ? "검색할 프로젝트 명을 입력하세요" : search;

        model.addAttribute("endRow", endRow);
        model.addAttribute("search", defaultSearch);
        model.addAttribute("searchType",searchType);
        model.addAttribute("listType", listType(listType));
        model.addAttribute("userName", user.getName()); // 사용자명
        model.addAttribute("projectList", projectList);
        model.addAttribute("orderList", orderList); // 주문목록
        model.mergeAttributes(getPagenation(pageNo,maxPage));

        System.out.println("searchType"+searchType);
        System.out.println("search"+search);


        return "/seller/sellerOrderList";
    }

    @GetMapping("/projectQnAList")
    public String getProjectQnAList(@RequestParam(required = false) Optional<String> listType,
                                    @RequestParam(required = false) String searchType,
                                    @RequestParam(required = false) String search,
                                    @RequestParam(required = false) Integer pageNo,
                                    Principal principal, Model model) throws AccessAuthorityException {

        if(principal == null) throw new AccessAuthorityException("접근 권한이 없습니다.");

        if(listType.isEmpty()){
            return "redirect:/seller/projectQnAList?listType=all";
        }

        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        if(!user.getStatus().equals(MemberStatus.USE.toString())) {
            throw new AccessAuthorityException("접근 권한이 없습니다.");
        }

        if(pageNo == null ) pageNo = 1;

        int startNo = 1 + ((pageNo - 1) * DEFAULT_MAX_ROWS);
        int endNo = ((pageNo) * DEFAULT_MAX_ROWS);

        Map<String, Object> searchCriteria = new HashMap<>();

        searchCriteria.put("listType",listType.get().toString());
        searchCriteria.put("searchType",searchType);
        searchCriteria.put("search", search);
        searchCriteria.put("userNo", user.getNo());
        searchCriteria.put("startNo", startNo);
        searchCriteria.put("endNo", endNo);

        List<ProjectQnADTO> projectQnAList = projectQnAService.selectByListTypeAndSearchProjectQnA(searchCriteria);

        int count = projectQnAService.selectByListTypeAndSearchProjectQnACount(searchCriteria);
        int endRow = count - ((pageNo -1) * DEFAULT_MAX_ROWS) < 0 ? count % DEFAULT_MAX_ROWS : count - ((pageNo - 1) * DEFAULT_MAX_ROWS);
        int maxPage = ((int) Math.floor((double) count / (double) DEFAULT_MAX_ROWS)) + (endRow % 10 == 0 ? 0 : 1);

        String defaultSearch = search == null ? "검색할 키워드를 입력하세요" : search;

        model.addAttribute("listType", listType(listType));
        model.addAttribute("userName",user.getName()); // 사용자명
        model.addAttribute("search",defaultSearch);
        model.addAttribute("searchType",searchType);
        model.addAttribute("projectQnAList", projectQnAList);
        model.addAttribute("endRow", endRow);

        model.mergeAttributes(getPagenation(pageNo,maxPage));
        return "/seller/sellerqa";
    }

    @GetMapping("deleteTempProject")
    @ResponseBody
    public String deleteTempProject(@RequestParam int no, Principal principal) throws AccessAuthorityException {

        UserImpl user = (UserImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        if(user.getStatus().equals(MemberStatus.DROP.toString())) {
            throw new AccessAuthorityException("접근 권한이 없습니다.");
        }

        boolean validationProject = projectService.existProjectByProjectNo(no, user.getNo());

        if(!validationProject) return "fail";

        ProjectProgressDTO projectProgress = projectProgressService.progressLastStatusById(no, null);

        if (!projectProgress.getStatus().equals(ProjectProgressStatus.TEMPORARY)) return "fail";

        List<ProjectFileDTO> projectFileList = projectFileService.selectProjectFileListByProjectNo(no, user.getNo());

        int result = projectService.deleteProjectByProjectNo(no);

        if(result == 0 ) return "fail";

        projectFileList.stream().forEach(e->{
            imageFileController.deleteFile("project", no, e.getChangeName());
        });

        return "success";
    }

}
