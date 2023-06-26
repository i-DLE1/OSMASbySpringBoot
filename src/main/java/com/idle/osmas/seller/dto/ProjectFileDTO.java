package com.idle.osmas.seller.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ProjectFileDTO {

    private int no; // pk

    private int projectNo;

    private ProjectFileType type; // 사용처

    private String originName; // 원본파일

    private String changeName; // 변경 파일

    private char deleteYN; // 삭제여부

    private LocalDate registDate; // 등록일자
}
