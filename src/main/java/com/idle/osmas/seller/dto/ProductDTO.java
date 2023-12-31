package com.idle.osmas.seller.dto;

import lombok.*;
import lombok.experimental.PackagePrivate;

import java.io.PipedReader;
import java.util.List;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ProductDTO {

    private int no; // pk

    private String name; // 상품명

    private String introduction; // 상품소개

    private long price; // 금액

    private int maxQuantity; // 최대수량

    private ProductStatus status; // 상품 주문 가능 상태

    private String size; // 상품 사이즈

    private Integer count; // 주문 수량

    private Integer refProjectNo;

}
