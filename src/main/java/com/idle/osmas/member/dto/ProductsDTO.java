package com.idle.osmas.member.dto;

import com.idle.osmas.seller.dto.ProductStatus;
import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ProductsDTO {

    private int productNo; // productNO

    private String name; // 상품명

    private String introduction; // 상품소개

    private long price; // 금액

    private int maxQuantity; // 최대수량

    private ProductStatus status; // 상품 주문 가능 상태

    private String size; // 상품 사이즈

    private Integer count; // 주문 수량

    private Integer refProjectNo;

}
