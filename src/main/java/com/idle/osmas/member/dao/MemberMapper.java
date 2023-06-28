package com.idle.osmas.member.dao;

import com.idle.osmas.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    String selectMemberById(String id);

    String selectMemberByNickname(String nickname);
    int insertMember(MemberDTO member);



}
