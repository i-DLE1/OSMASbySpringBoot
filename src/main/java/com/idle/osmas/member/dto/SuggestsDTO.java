package com.idle.osmas.member.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SuggestsDTO {
    private int no;
    private String title;
    private String content;
    private String nickname;
    private String status;
    private java.sql.Date modifyDate;
    private String name; // 카테고리 이름
    private int refCategoryNo;
    private int refMemberNo;
}
