package com.idle.osmas.member.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;
@Getter
@ToString
public class UserImpl extends User {

    private int no;     // 회원번호
    private String id;
    private String pwd;
    private String name;
    private String phone;
    private java.sql.Date birth;
    private String email;
    private java.sql.Date registDate; //가입날짜
    private String nickname;
    private String status;  // 회원상태
    private String introduction; // 소개
    private String dropReason; // 탈퇴사유

    private List<RoleListDTO> memberRoleList; // 회원별 권한 리스트
    public UserImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public void setDetails(MemberDTO member){
        this.no = member.getNo();
        this.pwd = member.getPwd();
        this.name = member.getName();
        this.phone = member.getPhone();
        this.birth = member.getBirth();
        this.email = member.getEmail();
        this.registDate = member.getRegistDate();
        this.nickname = member.getNickname();
        this.status = member.getStatus();
        this.introduction = member.getIntroduction();
        this.dropReason = member.getDropReason();
    }
}
