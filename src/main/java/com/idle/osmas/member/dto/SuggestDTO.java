package com.idle.osmas.member.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SuggestDTO {
    private int no;
    private String title;
    private String content;
    private java.sql.Date registDate;
    private int refMemberNo;
    private String status;
    private java.sql.Date modifyDate;
    private int refCategoryNo;
}
