package com.idle.osmas.seller.dto;
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

//    private MemberDTO member; DTO 추가되면 수

    //    private ProjectQnAAnswerDTO projectQnAAnswer; // 답변


}
