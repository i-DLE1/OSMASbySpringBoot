package com.idle.osmas.admin.dto;

import com.idle.osmas.member.dto.SuggestDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class  HoldingAlertDTO {
    private int no;
    private String content;
    private java.sql.Date registDate;
    private int refMemberNo;
    private int refPermissionRoleNo;
}
