package com.idle.osmas.seller.dto;
import com.idle.osmas.member.dto.MemberDTO;
import com.idle.osmas.member.dto.PaymentDTO;
import lombok.*;

import java.security.spec.RSAOtherPrimeInfo;
import java.time.LocalDate;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ProjectQnADTO {
    private int no; // pk

    private String content; // 본문

    private LocalDate registDate; // 등록일자

    private MemberDTO member;

    private ProjectQnAAnswerDTO projectQnAAnswer; // 답변

    private ProjectDTO project;

    private PaymentDTO payment;

}
