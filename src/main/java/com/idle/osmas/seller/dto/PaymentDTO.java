package com.idle.osmas.seller.dto;

import lombok.*;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PaymentDTO {

    private int code;
    private LocalDate paymentDate;
    private String status;
    private int amount;
    private String paymentType;

}
