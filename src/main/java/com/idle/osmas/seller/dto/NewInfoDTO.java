package com.idle.osmas.seller.dto;

import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class NewInfoDTO {

    private int no;
    private String title;
    private String content;
    private LocalDate registDate;
    private LocalDate modifyDate;
    private char deleteYn;
    private int refprjNo;

}
