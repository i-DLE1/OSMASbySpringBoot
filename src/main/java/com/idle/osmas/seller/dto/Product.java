package com.idle.osmas.seller.dto;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private int no; // pk

    private String name; // 상품명

    private String introduction; // 상품소개

    private int price; // 금액

    private int maxQuantity; // 최대수량

    private ProductStatus status; // 상품 주문 가능 상태

    private String size; // 상품 사이즈
}
