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
    // 중복검사용


    @Override
    public int selectNoByNickname(String nickname) {
        return mapper.selectNoByNickname(nickname);
    }

    @Override
    public String selectNicknameById(String id) {
        return mapper.selectNicknameById(id);
    }

    @Override
    public boolean selectMemberById(String id){
        String result = mapper.selectMemberById(id);
        return result != null? true : false;
    }

    @Override
    public boolean selectMemberByNickname(String nickname) {
        String result = mapper.selectMemberByNickname(nickname);
        return result != null? true : false;
    }

    @Override
    public boolean selectMemberByEmail(String email) {
        String result = mapper.selectMemberByEmail(email);
        return result != null? true : false;
    }
    // 이메일로 아이디를 찾은후 이메일 전송

    @Override
    @Transactional
    public void signUpMember(MemberDTO member) throws Exception {
        System.out.println(member);
        int result = mapper.insertMember(member);
        int result2 = mapper.insertRole();
        if(result>0 || result2 >0){
            System.out.println("회원가입에 성공");
        } else{
            throw new Exception("회원가입에 실패하셨습니다");
        }
    }


}
