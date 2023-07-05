package com.idle.osmas.seller.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SellerRollDTO {
    private String accountNo; //계좌번호

    private String registNo; //사업자 등록번호

    private String name; //회사명

    private String callNumber; //대표번호

    private String rprsn; //대표자명

    private String address; //주소

    private int memberNo; //회원번호

    private String bank; //은행

    private String reportNo; //통신판매업신고번호
}
