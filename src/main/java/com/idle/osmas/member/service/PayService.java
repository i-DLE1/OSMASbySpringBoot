package com.idle.osmas.member.service;

import com.idle.osmas.member.dto.*;
import com.idle.osmas.seller.dto.ProductDTO;

public interface PayService {

    public MemberDTO selectMemberById(String id);
    public AddressDTO selectAddressByNo(int no);

    public String insertAddress(AddressDTO address) throws Exception;

    public String modAddress(AddressDTO address) throws Exception;

    public ProductsDTO selectProduct(int productNo);

    public PayDTO selectPay(int no);

    public ReadyResponse payReady(PayInfo pay,String user_id);


}
