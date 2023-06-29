package com.idle.osmas.admin.dto;

import com.idle.osmas.admin.dto.AdminBoardDTO;
import com.idle.osmas.member.dto.MemberDTO;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AdminBoardDTO {

    private int no;  // index

    private String title; // 제목

    private String content; // 컨텐츠

    private LocalDate registDate; // 게시판 등록일

    private LocalDate modifyDate; // 게시판 수정일

    private AdminBoardDivisionCode classifyCode; //구분코드

    private MemberDTO memberNo;  //작성자인데.....리스트로 가져와야하나(수정필수)

    private char deleteYN; //삭제여부

}
