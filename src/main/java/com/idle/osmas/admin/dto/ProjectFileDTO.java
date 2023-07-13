package com.idle.osmas.admin.dto;

import com.idle.osmas.seller.dto.ProjectFileType;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProjectFileDTO {

    private int no; // pk

    private String originName; // 원본파일

    private String changeName; // 변경 파일

    private char deleteYN; // 삭제여부

    private LocalDate registDate; // 등록일자

}
