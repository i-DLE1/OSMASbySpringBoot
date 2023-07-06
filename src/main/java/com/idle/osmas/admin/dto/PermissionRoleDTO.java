package com.idle.osmas.admin.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PermissionRoleDTO {

    private int no; //승인내역코드

    private String rejectReason; //사유

    private PermissionRoleStatus permissionStatus; //상태값

    private LocalDate lastDate; //처리일자

    private SellerRoleReqDTO sellerRoleReq; //판매자권한신청코드

    private String sellerReason; //회수 신청 사유

    private String otherReason; //회수 신청 기타 사유



}
