package com.idle.osmas.seller.dto;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectQnAAnswerDTO {

    private int no; // PK No

    private String content; // 본문

    private LocalDate registDate; // 등록날짜

}
