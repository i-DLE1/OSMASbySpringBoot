package com.idle.osmas.seller.dto;
import lombok.*;

import java.security.spec.RSAOtherPrimeInfo;
import java.time.LocalDate;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProjectQnADTO {
    private int no; // pk
    private String content; // 본문
    private LocalDate registDate; // 등록일자
}
