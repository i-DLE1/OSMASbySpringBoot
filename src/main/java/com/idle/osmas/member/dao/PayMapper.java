package com.idle.osmas.member.dao;

import com.idle.osmas.member.dto.AddressDTO;
import com.idle.osmas.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PayMapper {

    MemberDTO selectMemberById(String id);
    AddressDTO selectAddressByNo(int no);

    int insertAddress(AddressDTO address);
    int modAddress(AddressDTO address);
}
