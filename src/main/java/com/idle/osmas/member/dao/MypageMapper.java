package com.idle.osmas.member.dao;

import com.idle.osmas.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MypageMapper {
    MemberDTO selectMemberByNo(int no);
    int updateMemberbyNo(int no, String colName, String changeVal);
    int updateMemberStatusByNo(int no, String status, String reason);
}
