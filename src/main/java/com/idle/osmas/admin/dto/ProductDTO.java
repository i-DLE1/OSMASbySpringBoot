package com.idle.osmas.admin.dto;

import com.idle.osmas.member.dto.MemberDTO;
import lombok.*;


@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ProductDTO {

    private int no; // pk

    private String title; // 상품명

    private String content; // 상품소개

    private int registDate; // 등록일

    private int startDate; //시작 날짜

    private MemberDTO member;

    public void setMember(MemberDTO member) {
        this.member = member;
    }

    private ProductProgressDTO productProgress;

}
