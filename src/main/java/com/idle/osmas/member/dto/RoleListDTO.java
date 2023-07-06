package com.idle.osmas.member.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RoleListDTO {
    private int refMemberRoleCode;
    private int refMemberNo;

    private MemberRoleDTO memberRole;

}
