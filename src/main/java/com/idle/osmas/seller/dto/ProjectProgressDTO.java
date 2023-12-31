package com.idle.osmas.seller.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProjectProgressDTO {

    private int no; // pk

    private String content; // 본문

    private LocalDate registDate; // 등록일자

    private ProjectProgressStatus status; // 진행상태

    private int refProjectNo;

}
