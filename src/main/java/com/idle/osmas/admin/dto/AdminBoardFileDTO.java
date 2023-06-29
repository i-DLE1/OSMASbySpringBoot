package com.idle.osmas.admin.dto;

import com.idle.osmas.member.dto.MemberDTO;
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

    //추가
    private int boardNo; // 게시판 번호

    private String boardTitle; // 게시판 제목

    private String boardContent; // 게시판 내용

    private LocalDate boardRegistDate; // 게시판 등록일

    private LocalDate boardModifyDate; // 게시판 수정일

    private char boardDeleteYN; // 게시판 삭제 여부

    private AdminBoardDivisionCode boardClassifyCode; // 게시판 구분 코드

    private MemberDTO boardMemberNo; // 게시판 작성자

}
