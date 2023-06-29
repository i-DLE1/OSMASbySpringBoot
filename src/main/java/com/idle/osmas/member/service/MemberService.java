package com.idle.osmas.member.service;

import com.idle.osmas.member.dto.MemberDTO;

public interface MemberService {

    // 회원 가입용
    public void signUpMember(MemberDTO member) throws Exception;

    // 회원 조회용
    public boolean selectMemberById(String id);

    public boolean selectMemberByNickname(String nickname);

    public boolean selectMemberByEmail(String email);

}
