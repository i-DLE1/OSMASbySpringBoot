package com.idle.osmas.seller.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {

    private int no;
    private String title;
    private String content;

    private LocalDate startDate;
    private LocalDate endDate;
    private int money;


}
