package com.idle.osmas.admin.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SellerRoleReqDTO {

    private int no; //판매자권한신청코드

    private SellerRoleDTO memberNo; //판매자번호


    private LocalDate date; //신청일자
}
