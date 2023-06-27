package com.idle.osmas.member.dto;

import lombok.*;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PaymentDTO {
    private int code;
    private java.sql.Date paymentDate;
    private String status;
    private Integer amount;
    private String paymentType;
    private List<ShippingTrackInfoDTO> shippingTrackInfo;
    private List<PaymentHistoryDTO> paymentHistory;
}
