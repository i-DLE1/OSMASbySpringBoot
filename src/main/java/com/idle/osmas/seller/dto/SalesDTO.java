package com.idle.osmas.seller.dto;

import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SalesDTO {

    private int no;
    private String title;
    private String content;
    private LocalDate registDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long targetAmount;
    private Long currentAmount;
    private Integer views;
    private int refMemberNo;
    private int refCategoryNo;


}
