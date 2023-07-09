package com.idle.osmas.admin.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TermsDTO {

    private int no; //약관번호

    private String title; //제목

    private String content; //내용

    private LocalDate registDate; //생성날짜

    private TermsDivisionCode classifyCode; //구분코드

    private int termsIndex; //순번....이뭐지

    private TermsHistoryDTO termsHistory;

}
