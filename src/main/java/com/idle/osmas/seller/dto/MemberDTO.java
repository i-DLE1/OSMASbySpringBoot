package com.idle.osmas.seller.dto;

import lombok.*;

import java.time.LocalDate;
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
    private LocalDate registDate; //가입날짜
    private String nickname;
    private String status;  // 회원상태
    private String introduction; // 소개
    private String dropReason; // 탈퇴사유



}
