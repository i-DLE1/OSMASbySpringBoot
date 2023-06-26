package com.idle.osmas.member.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberDTO {
    private int no;     // 회원번호
    private String id;
    private String pwd;
    private String name;
    private String phone;
    private java.sql.Date birth;
    private String email;
    private java.sql.Date registDate; //가입날짜
    private String nickname;
    private String status;  // 회원상태
    private String introduction; // 소개
    private String dropReason; // 탈퇴사유

    private List<RoleListDTO> memberRoleList; // 회원별 권한 리스트
    private List<SponsoredProjectDTO> sponsoredList; // 후원한 프로젝트 리스트
    private List<ShippingTrackInfoDTO> shippingTrackInfoList; // 배송정보 리스트
    private List<ReviewDTO> reviewList; // 펀딩후기 리스트
    private List<AddressDTO> addressList; // 주소리스트
    private List<SuggestDTO> suggestList; // 제안하기 리스트
    private List<ProjectWishDTO> projectWishList; // 프로젝트 찜하기 리스트
    // 프로젝트 q/a 필
}
