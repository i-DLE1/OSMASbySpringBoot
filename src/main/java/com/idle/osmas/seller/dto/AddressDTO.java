package com.idle.osmas.seller.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddressDTO {

    private String general;
    private String detail;
    private String postalCode;
    private int no;
    private int refMemberNo;

}
