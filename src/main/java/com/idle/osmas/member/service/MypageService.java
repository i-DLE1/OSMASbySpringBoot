package com.idle.osmas.member.service;

import com.idle.osmas.member.dao.MypageMapper;
import com.idle.osmas.member.dto.MemberDTO;
import com.idle.osmas.member.dto.MemberStatus;
import org.springframework.stereotype.Service;


public interface MypageService {

    MemberDTO selectMemberByNo(int no);

    int updateMemberbyNo(int no, String colName, String changeVal);

    int updateMemberStatusByNo(int no, MemberStatus status, String reason);

    MemberDTO selectJoinByNo(int no);

    int allInfoByNO(int no, String name, String phone, String general, String detail, String postCode);

    int updatePwdStatusByNo(int no, String pwd);
}
