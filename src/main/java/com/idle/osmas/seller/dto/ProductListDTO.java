package com.idle.osmas.seller.dto;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ProductListDTO {

    private int no;

    private ProjectDTO project;

    private List<ProductDTO> productList;
}
