package com.idle.osmas.seller.dto;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProjectFAQDTO {

    private int no; // index

    private String title; // FAQ 제목

    private String content; // 본문

    private LocalDate registDate; // 등록날짜
}
