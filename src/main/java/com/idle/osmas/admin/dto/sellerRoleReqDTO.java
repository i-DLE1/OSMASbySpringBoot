package com.idle.osmas.admin.dto;

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

    private RoleListDTO memberNo; //판매자번호 -->판매자 번호 여기서 가져오는거 맞는지 확인 필요

    private LocalDate date; //신청일자
}
