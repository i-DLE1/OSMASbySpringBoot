package com.idle.osmas.admin.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class permissionRoleDTO {

    private int no; //승인내역코드

    private String rejectReason; //사유

    private permissionRoleStatus permissionStatus; //상태값

    private LocalDate lastDate; //처리일자

    private sellerRoleReqDTO sellerRoleReq; //판매자권한신청코드


}
