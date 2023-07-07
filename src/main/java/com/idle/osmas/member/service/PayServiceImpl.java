package com.idle.osmas.member.service;

import com.idle.osmas.member.dao.PayMapper;
import com.idle.osmas.member.dto.AddressDTO;
import com.idle.osmas.member.dto.MemberDTO;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements PayService{

    private final PayMapper mapper;

    public PayServiceImpl(PayMapper mapper){
        this.mapper = mapper;
    }
    @Override
    public MemberDTO selectMemberById(String id) {
        return mapper.selectMemberById(id);
    }

    @Override
    public AddressDTO selectAddressByNo(int no) {
        return mapper.selectAddressByNo(no);
    }
}
