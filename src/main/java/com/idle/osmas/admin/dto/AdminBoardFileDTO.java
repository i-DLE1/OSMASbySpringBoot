package com.idle.osmas.admin.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AdminBoardFileDTO {

    private int no; //관리자게시판파일번호

    private String originName; //원본파일명

    private String changeName; //변경된파일명

    private LocalDate registDate; // 등록일

    private char deteleYN; //삭제여부

    private AdminBoardDTO adminBoardNo; //게시판 번호
}
