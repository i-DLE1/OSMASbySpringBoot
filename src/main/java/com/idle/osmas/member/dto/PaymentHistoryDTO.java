package com.idle.osmas.member.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PaymentHistoryDTO {
    private int refPaymentNo;
    private int refSponsoredPrjNo;

    private SponsoredProjectDTO sponsoredProject;
}
