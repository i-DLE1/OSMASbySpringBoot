package com.idle.osmas.admin.dto;

import com.idle.osmas.seller.dto.ProjectProgressStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductProgressDTO {
    private int no; // pk
    private String content; // 본문
    private LocalDate registDate; // 등록일자
    private ProjectProgressStatus status; // 진행상태
    private ProductDTO productNo; //프로젝트 등록번호 확인용

}