package com.idle.osmas.member.service;

import com.idle.osmas.member.dto.ProductsDTO;
import com.idle.osmas.seller.dto.ProductDTO;
import com.idle.osmas.member.dto.AddressDTO;
import com.idle.osmas.member.dto.MemberDTO;
import com.idle.osmas.member.dto.PayDTO;

public interface PayService {

    public MemberDTO selectMemberById(String id);
    public AddressDTO selectAddressByNo(int no);

    public String insertAddress(AddressDTO address) throws Exception;

    public String modAddress(AddressDTO address) throws Exception;

    public ProductsDTO selectProduct(int productNo);

    public PayDTO selectPay(int no);
}
