package com.idle.osmas.seller.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

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

    private int targetAmount; // 목표금액

    private int currentAmount; // 현재금액

    private LocalDate registDate; // 프로젝트 등록일자

    private int view; // 뷰 수

}
