package com.idle.osmas.member.dto;

import com.idle.osmas.seller.dto.ProjectDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProjectWishDTO {
    private int refMemberNo;
    private int refProjectNo;
    private ProjectDTO project;

}
