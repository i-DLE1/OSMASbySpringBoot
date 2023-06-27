package com.idle.osmas.member.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddressDTO {
    private int no;
    private String general;
    private String detail;
    private String PostalCode;
    private Integer shipNo;
    private Integer refMemberNo;
}
