package com.idle.osmas.seller.dto;
import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ProjectCategoryDTO {

    private int no; // PK

    private Integer subNo;

    private String name;

    private ProjectCategoryDTO subCategory;
}
