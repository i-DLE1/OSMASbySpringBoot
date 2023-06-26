package com.idle.osmas.admin.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class sellerRoleFileDTO {

    private int no; //판매자정보파일번호

    private sellerRoleFileDivisionCode idCode; //구분 코드

    private String originName; //원본파일명

    private String changeName; //변경된파일명

    private  char deteleYN; //사용가능

    private MemberDTO memberNo; //회원번호 -->일케 가져오는거 맞는지
}
