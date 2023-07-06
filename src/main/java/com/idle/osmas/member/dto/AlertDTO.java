package com.idle.osmas.member.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AlertDTO {
    private int no;
    private String content;
    private java.sql.Date registDate;
    private int refMemberNo;
    private int refSuggestNo;
    private SuggestDTO suggest;
}
