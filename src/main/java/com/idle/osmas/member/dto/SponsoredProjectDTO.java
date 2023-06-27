package com.idle.osmas.member.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SponsoredProjectDTO { // 후원한 프로젝트
    private int no;
    private java.sql.Date registDate;
    private Integer amount;
    private int refProjectProductListNo;
    private int refMemberNo;
}
