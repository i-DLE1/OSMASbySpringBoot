package com.idle.osmas.seller.dto;

import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DeliveryStatusDTO {

    private DeliveryStatus status;
    private LocalDate registDate;
    private int refShippingTrackInfoNo;
    private int no;
}
