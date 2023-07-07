package com.idle.osmas.seller.dto;

import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class QnaAnswerDTO {
    private int no;
    private String content;
    private LocalDate registDate;
    private int prjQnaCode;
    private int refMemberNo;

}
