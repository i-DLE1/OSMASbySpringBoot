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
}
