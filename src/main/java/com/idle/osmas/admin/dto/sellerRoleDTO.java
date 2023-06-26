package com.idle.osmas.admin.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class sellerRoleDTO {

    private String accountNo; //계좌번호

    private String registNo; //사업자 등록번호

    private String name; //회사명

    private String callNumber; //대표번호

    private String rprsn; //대표자명

    private String address; //주소

    //private MemberDTO memberNo; //회원번호 -->일케 가져오는거 맞는지

    private String bank; //은행

    private String reportNo; //통신판매업신고번호
}
