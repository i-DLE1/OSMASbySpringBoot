package com.idle.osmas.admin.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TermsHistoryDTO {

    private int no; //변경이력코드

    private String content; //변경내용

    private LocalDate lastDate; //변경날짜

    private TermsDTO termsNo; //약관번호

}
