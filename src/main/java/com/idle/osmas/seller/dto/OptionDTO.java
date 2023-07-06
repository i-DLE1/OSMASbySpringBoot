package com.idle.osmas.seller.dto;

import lombok.*;
import java.text.DecimalFormat;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OptionDTO {

    private int no;
    private String name;
    private String introduction;
    private int price;
    private int maxQuantity;
    private String status;
    private String productSize;

}
