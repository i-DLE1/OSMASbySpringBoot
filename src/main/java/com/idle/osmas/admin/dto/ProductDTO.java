package com.idle.osmas.admin.dto;

import com.idle.osmas.member.dto.MemberDTO;
import com.idle.osmas.admin.dto.ProjectFileDTO;
import lombok.*;


@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private int no; // pk
    private String title; // 상품명
    private String content; // 상품소개
    private int registDate; // 등록일
    private int startDate; // 시작 날짜
    private MemberDTO member;
    private ProductProgressDTO productProgress;
    private ProjectFileDTO file;

}