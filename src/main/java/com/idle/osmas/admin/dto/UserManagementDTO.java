package com.idle.osmas.admin.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserManagementDTO {

    private int no; //회원번호

    private String id;

    private String pwd;

    private String name;

    private String nickname;

    private String phone;

//    private java.sql.Date brith;

    private Date birth;

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    private String email;

    private java.sql.Date registDate;

    private String status; //회원상태

    private String dropReason; //탈퇴사유



}
