package com.idle.osmas.seller.dto;


import com.idle.osmas.member.dto.MemberDTO;
import com.idle.osmas.member.dto.PaymentDTO;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectQnAAnswerDTO {

    private int no; // PK No

    private String content; // 본문

    private LocalDate registDate; // 등록날짜

//    private MemberDTO member; memberDTO 추가되면 수정 예정

//    private ProjectQnADTO projectQnA; // QnA

    private MemberDTO member;

//    private PaymentDTO payment;

//    private ProjectDTO project;

}
