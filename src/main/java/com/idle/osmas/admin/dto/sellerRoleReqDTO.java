package com.idle.osmas.admin.dto;

import com.idle.osmas.member.dto.RoleListDTO;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class sellerRoleReqDTO {

    private int no; //판매자권한신청코드

    private RoleListDTO memberNo; //판매자번호

    private LocalDate date; //신청일자
}
