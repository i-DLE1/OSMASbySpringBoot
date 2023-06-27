package com.idle.osmas.member.service;

import com.idle.osmas.member.dao.MemberMapper;
import com.idle.osmas.member.dto.MemberDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class MemberServiceImpl implements MemberService{

    private final MemberMapper mapper;

    public MemberServiceImpl(MemberMapper mapper){
        this.mapper = mapper;
    }


    @Override
    @Transactional
    public void signUpMember(MemberDTO member) {
        System.out.println(member);
        int result = mapper.insertMember(member);
        if(result>0){
            System.out.println("회원가입에 성공");
        } else{
            System.out.println("회원가입 실패");
        }

    }
}
