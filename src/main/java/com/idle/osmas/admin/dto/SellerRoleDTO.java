package com.idle.osmas.admin.dto;

import com.idle.osmas.member.dto.MemberDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SellerRoleDTO {

    private String accountNo; //계좌번호

    private String registNo; //사업자 등록번호

    private String name; //회사명

    private String callNumber; //대표번호

    private String rprsn; //대표자명

    private String address; //주소

    private int memberNo; //회원번호 -->일케 가져오는거 맞는지

    private String bank; //은행

    private String reportNo; //통신판매업신고번호

    private String memberId; //회원 아이디 --> 이거 번호랑 짝꿍

    private String alert; //보류 메세지 (보류 걸러내는용) --> 이거 권한 승인되면 null값으로 다시 바꿔줘야함

    private int retrieve; //권한 회수 신청(0이면 신청X 1이면 신청) 나중에 회수 되어도 1로 냅두기

    private MemberDTO member; //멤버........담기.....

    private List<SellerRoleFileDTO>  fileList; //신청한 파일 목록
}
