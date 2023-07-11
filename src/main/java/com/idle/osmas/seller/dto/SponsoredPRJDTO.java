package com.idle.osmas.seller.dto;

import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SponsoredPRJDTO {

    private LocalDate registDate;
    private int amount;
    private int no;
    private int refPrjProductListNo;
    private int refMemberNo;

    private MemberDTO memberName;
    private AddressDTO general;
    private AddressDTO detail;
    private ShippingTrackInfoDTO request;
    private MemberDTO phone;
    private ProductDTO productName; // 상품이름
    private ProductDTO productSize;
    private PaymentDTO paymentAmount; // 결제금액
    private PaymentDTO paymentType;
    private PaymentDTO paymentDate;
    private DeliveryStatusDTO deliveryStatus;

}
