package com.idle.osmas.seller.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProjectFileDTO {

    private int no; // index

    private ProjectFileType type; // 사용처

    private String originFile; // 원본파일

    private String changeFile; // 변경 파일

    private char deleteYN; // 삭제여부

    private LocalDate registDate; // 등록일자
}
