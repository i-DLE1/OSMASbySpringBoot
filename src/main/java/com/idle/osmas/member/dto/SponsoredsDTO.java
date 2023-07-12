package com.idle.osmas.member.dto;

import lombok.*;

// 후원한 프로젝트 dto
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SponsoredsDTO {
    private int no;
    private String title;
    private java.sql.Date registDate;
    private String nickname;
    private int refPrjProductListNo;
    private int memberNo;
    private String productName;
    private int refPrjNo; // 프로젝트 번호
    private String changeName;          // 사진
}
