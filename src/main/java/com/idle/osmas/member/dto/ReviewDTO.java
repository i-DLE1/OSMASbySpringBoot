package com.idle.osmas.member.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReviewDTO {
    private int refDeliveryStatusCode;
    private int refMemberNo;
    private String content;
    private String title;
    private java.sql.Date registDate;
    private java.sql.Date modifyDate;
    private char deteleYn;


}
