package com.idle.osmas.member.service;

import com.idle.osmas.member.dto.*;
import com.idle.osmas.seller.dto.ProductDTO;
import com.idle.osmas.member.dao.PayMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

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

    public ReadyResponse payReady(PayInfo pay,String user_id){

        String order_id = "111111111";

        MultiValueMap<String,String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", "TC0ONETIME"); // 테스트시 가맹점 코드로 사용
        parameters.add("partner_order_id", order_id);
        parameters.add("partner_user_id",user_id);
        parameters.add("item_name",pay.getTitle()); // 상품 이름
        parameters.add("quantity","1"); // 상품 수량
        parameters.add("total_amount", String.valueOf(pay.getPrice()));// 상품 총액
        parameters.add("tax_free_amount", "0");
        parameters.add("approval_url", "http://localhost:8080/member/pay/paysuccess"); // 결제승인시 넘어갈 url
        parameters.add("cancel_url", "http://localhost:8080/member/pay/cancel"); // 결제취소시 넘어갈 url
        parameters.add("fail_url", "http://localhost:8080/member/pay/fail"); // 결제 실패시 넘어갈 url

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        RestTemplate template = new RestTemplate();
        String url = "https://kapi.kakao.com/v1/payment/ready";
        ReadyResponse readyResponse = template.postForObject(url, requestEntity, ReadyResponse.class);
        return readyResponse;
    }

    private HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK 417ea429496f34db62fc8863263221c5");
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        return headers;
    }
}
