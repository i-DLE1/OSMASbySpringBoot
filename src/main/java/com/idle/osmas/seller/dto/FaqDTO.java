package com.idle.osmas.seller.dto;

import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FaqDTO {

    private int no;
    private int refPrjNo;
    private String title;
    private String content;
    private LocalDate registDate;

}
