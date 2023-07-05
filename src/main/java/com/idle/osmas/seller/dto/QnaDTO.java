package com.idle.osmas.seller.dto;

import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class QnaDTO {
    private int no;
    private String content;
    private int refMemberNo;
    private int refPrjNo;
    private LocalDate registDate;

    private MemberDTO name;
    private QnaAnswerDTO answerContent;
    private QnaAnswerDTO answerRegistDate;
    private QnaAnswerDTO answerNo;
    private MemberDTO answerName;
}
