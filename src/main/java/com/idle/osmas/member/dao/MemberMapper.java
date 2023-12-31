package com.idle.osmas.member.dao;

import com.idle.osmas.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    String selectMemberById(String id);
    String selectMemberByNickname(String nickname);

    int selectNoByNickname(String nickname);

    String selectMemberByEmail(String email);
    int insertMember(MemberDTO member); // 회원 가입 용

    String selectNicknameById(String id);
    int insertRole(); // 회원 가입시 User권한 자동 부여

    String selectIdByEmail(String email);

    String selectPwdByEmail(String email);

    int updatePwd(MemberDTO member);



}
