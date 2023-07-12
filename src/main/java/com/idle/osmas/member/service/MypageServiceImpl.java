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

    @Override
    public MemberDTO selectJoinByNo(int no) {
        return mypageMapper.selectJoinByNo(no);
    }

    @Override
    @Transactional
    public int allInfoByNO(int no, String name, String phone, String general, String detail, String postCode) {

        Integer result = mypageMapper.selectAddressSearchByNo(no);

        int allNum = 0;

        if( result == null ) {
            allNum = mypageMapper.insertAddressByNo(general, detail, postCode, no);
        } else {
            allNum = mypageMapper.updateAddressStatusByNo(result, general, detail, postCode);
        }
            allNum += mypageMapper.updateMemberInfoByNo(no, name, phone);

        if (allNum == 2 ) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int updatePwdStatusByNo(int no, String pwd) {
        return mypageMapper.updatePwdStatusByNo(no, pwd);
    }
}
