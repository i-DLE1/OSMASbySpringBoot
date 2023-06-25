package com.idle.osmas.seller.dto;
import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProjectCategory {
    private int no; // PK

    private int subNo;

    private String name;

    private ProjectCategory subCategory;
}
