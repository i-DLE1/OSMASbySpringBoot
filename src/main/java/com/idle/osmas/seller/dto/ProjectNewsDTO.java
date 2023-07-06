package com.idle.osmas.seller.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ProjectNewsDTO {

    private int no; // index_pk

    private String title; // 제목

    private String content; // 본문

    private LocalDate registDate; // 등록일자

    private LocalDate modifyDate; // 수정일자

    private char deleteYN; // 삭제 여부

    private int projectNo;
    
}
