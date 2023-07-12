package com.idle.osmas.seller.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ShippingTrackInfoDTO {

    private int no;
    private int refMemberNo;
    private String corp;
    private String docNo;
    private String request;
    private int refPaymentNo;

}
