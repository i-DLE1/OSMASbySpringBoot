package com.idle.osmas.member.dao;

import com.idle.osmas.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    int insertMember(MemberDTO member);
}
