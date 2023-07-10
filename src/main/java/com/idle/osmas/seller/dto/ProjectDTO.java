package com.idle.osmas.seller.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.idle.osmas.member.dto.MemberDTO;
import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {

    private int no;  // index

    private String title; // 제목

    private String content; // 컨텐츠

    private LocalDate startDate; // 프로젝트 시작일

    private LocalDate endDate; // 프로젝트 종료일

    private Long targetAmount; // 목표금액

    private Long currentAmount; // 현재금액

    private LocalDate registDate; // 프로젝트 등록일자

    private Integer views; // 뷰 수

    private int refMemberNo;

    private int refCategoryNo;

    private ProjectCategoryDTO category;

    private MemberDTO member;

    private  List<ProjectFAQDTO> projectFAQList;

    private List<ProjectFileDTO> projectFileList;

    private ProjectProgressDTO projectProgress;

    private List<ProjectNewsDTO> projectNewsList;

    private List<ProjectQnADTO> projectQnAList;

    private List<ProductDTO> productList;


}
