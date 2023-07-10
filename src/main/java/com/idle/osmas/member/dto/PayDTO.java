package com.idle.osmas.member.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PayDTO {
    private int no;  // projectNo
    private String title; // 제목
    private LocalDate endDate; // 프로젝트 종료일
    private Long targetAmount; // 목표금액
    private Long currentAmount; // 현재금액
    private long sumPrice;
}
