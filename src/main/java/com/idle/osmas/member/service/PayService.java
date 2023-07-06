package com.idle.osmas.member.service;

import com.idle.osmas.member.dto.AddressDTO;
import com.idle.osmas.member.dto.MemberDTO;

public interface PayService {

    public MemberDTO selectMemberById(String id);
    public AddressDTO selectAddressByNo(int no);
}
