package com.idle.osmas.member.service;

import com.idle.osmas.member.dto.ProductsDTO;
import com.idle.osmas.seller.dto.ProductDTO;
import com.idle.osmas.member.dao.PayMapper;
import com.idle.osmas.member.dto.AddressDTO;
import com.idle.osmas.member.dto.MemberDTO;
import com.idle.osmas.member.dto.PayDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLOutput;

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

    @Override
    public String insertAddress(AddressDTO address) throws Exception {
        int result = mapper.insertAddress(address);
        
        if(result >0){
            return "주소가 등록되었습니다";
        }else{
            throw new Exception("다시 입력해주시요");
        }
    }

    @Override
    public String modAddress(AddressDTO address) throws Exception {
        int result = mapper.modAddress(address);

        if(result >0){
            return "주소가 수정되었습니다";
        }else{
            throw new Exception("다시 입력해주시요");
        }
    }

    @Override
    public ProductsDTO selectProduct(int productNo) {
        return mapper.selectProduct(productNo);
    }

    @Override
    public PayDTO selectPay(int no) {
        return mapper.selectPay(no);
    }
}
