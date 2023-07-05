package com.idle.osmas.member.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReviewsDTO {
    private int refDeliveryStatusCode;
    private int refMemberNo;
    private String refProjectName; // 프로젝트 이름
    private String nickname;       // 닉네임
    private String content;
    private String title;
    private java.sql.Date registDate;
    private java.sql.Date modifyDate;
    private char deteleYn;
}
