package com.idle.osmas.member.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PayInfo {
    private int no;
    private List<Integer> productNo;
    private List<Integer> count;
    private long price;
    private String title;
    private String request;
}
