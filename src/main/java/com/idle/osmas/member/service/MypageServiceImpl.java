package com.idle.osmas.member.service;

import com.idle.osmas.member.dao.MypageMapper;
import com.idle.osmas.member.dto.MemberDTO;
import com.idle.osmas.member.dto.MemberStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MypageServiceImpl implements MypageService{
private final MypageMapper mypageMapper;
    public MypageServiceImpl(MypageMapper mypageMapper) {
        this.mypageMapper = mypageMapper;
    }
    @Override
    public MemberDTO selectMemberByNo(int no) {
        return mypageMapper.selectMemberByNo(no);
    }

    @Override
    @Transactional
    public int updateMemberbyNo(int no, String colName, String changeVal) {
        return mypageMapper.updateMemberbyNo(no, colName, changeVal);
    }
    @Override
    @Transactional //rollback , commit
    public int updateMemberStatusByNo(int no, MemberStatus status, String reason) {
        return mypageMapper.updateMemberStatusByNo(no, status, reason);
    }

}
