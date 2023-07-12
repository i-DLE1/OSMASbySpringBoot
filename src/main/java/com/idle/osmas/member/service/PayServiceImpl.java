package com.idle.osmas.member.service;

import com.idle.osmas.member.dao.MemberMapper;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PayServiceImpl implements PayService {

    private final PayMapper mapper;
    private final MemberMapper memberMapper;

    public PayServiceImpl(PayMapper mapper,MemberMapper memberMapper) {
        this.mapper = mapper;
        this.memberMapper = memberMapper;
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

        if (result > 0) {
            return "주소가 등록되었습니다";
        } else {
            throw new Exception("다시 입력해주시요");
        }
    }

    @Override
    public String modAddress(AddressDTO address) throws Exception {
        int result = mapper.modAddress(address);

        if (result > 0) {
            return "주소가 수정되었습니다";
        } else {
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

    public ReadyResponse payReady(PayInfo pay, String user_id) {

        String order_id = "111111111";

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", "TC0ONETIME"); // 테스트시 가맹점 코드로 사용
        parameters.add("partner_order_id", order_id);
        parameters.add("partner_user_id", user_id);
        parameters.add("item_name", pay.getTitle()); // 상품 이름
        parameters.add("quantity", "1"); // 상품 수량
        parameters.add("total_amount", String.valueOf(pay.getPrice()));// 상품 총액
        parameters.add("tax_free_amount", "0");
        parameters.add("approval_url", "http://localhost:8080/member/paysuccess"); // 결제승인시
        parameters.add("cancel_url", "http://localhost:8080/member/paycancel"); // 결제취소시
        parameters.add("fail_url", "http://localhost:8080/member/payfail"); // 결제 실패시

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        RestTemplate template = new RestTemplate();
        String url = "https://kapi.kakao.com/v1/payment/ready";
        ReadyResponse readyResponse = template.postForObject(url, requestEntity, ReadyResponse.class);
        return readyResponse;
    }

    @Override
    public ApproveResponse payApprove(String tid, String pgToken, PayInfo pay, String user_id) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.add("cid", "TC0ONETIME");
        parameters.add("tid", tid);
        parameters.add("partner_order_id", "111111111");
        parameters.add("partner_user_id", user_id);
        parameters.add("pg_token", pgToken);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
        RestTemplate template = new RestTemplate();
        String url = "https://kapi.kakao.com/v1/payment/approve";
        ApproveResponse approveResponse = template.postForObject(url, requestEntity, ApproveResponse.class);
        return approveResponse;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK 417ea429496f34db62fc8863263221c5");
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        return headers;
    }

    @Override
    @Transactional
    public boolean paySuccess(PayInfo pay, String user_id) throws Exception {
        Map<String,Integer> productMap = new HashMap<>();
        List<Integer> productNoList = pay.getProductNo();   // 상품번호 리스트
        int projectNo = pay.getNo();                        // 프로젝트 번호
        List<Integer> productCount = pay.getCount();        // 상품 개수
        Long price = pay.getPrice();                        // 총 가격
        int productListNo;                                  // 상품 리스트 번호
        SponsoredProjectDTO sponsoredProject = new SponsoredProjectDTO();// 후원한 프로젝트 생성
        int refMemberNo = memberMapper.selectNoByNickname(memberMapper.selectNicknameById(user_id));   // 회원번호 찾기

        if(pay.getRequest().equals("")){
            pay.setRequest("부재시 경비실에 맡겨주세요");
        }
        PaymentDTO payment = new PaymentDTO();
        payment.setAmount(pay.getPrice());
        int payResult = mapper.insertPayment(payment);      // 결제결과

        if(!(payResult>0)){
            throw new Exception("결제내욥 삽입이 안됨");
        }
        int refPaymentNo = payment.getCode();               // 결제코드 받기
        int refSponsoredPrjNo;                              // 후원한 프로젝트 코드
        int result;                                         // 후원한 프로젝트 결과
        int paymentHistoryResult;                          // 결제 내역

        Map<String,Integer> paymentHistory = new HashMap<>();   // 결재이력 넣어줄 맵
        for (int i = 0; i < productNoList.size(); i++) {
            productMap.put("projectNo",projectNo);
            productMap.put("productNo",productNoList.get(i));
            productMap.put("count",productCount.get(i));
            productListNo = mapper.selectProductListNo(productMap); // 상품 리스트 번호 찾기

            if(!(productListNo>=0)){
                throw new Exception("상품리스트 번호 찾기 실패");
            }

            sponsoredProject.setAmount(productCount.get(i));
            sponsoredProject.setRefMemberNo(refMemberNo);
            sponsoredProject.setRefProjectProductListNo(productListNo);

            result = mapper.insertSponsoredProject(sponsoredProject);

            if(!(result>0)){
                System.out.println("-===0--" + sponsoredProject.getNo());
                throw new Exception("후원한 프로젝트 삽입 실패");
            }

            refSponsoredPrjNo = sponsoredProject.getNo();


            paymentHistory.put("refSponsoredPrjNo",refSponsoredPrjNo);
            paymentHistory.put("refPaymentNo",refPaymentNo);
            paymentHistoryResult = mapper.insertPaymentHistory(paymentHistory); // 결제 내역
            if(!(paymentHistoryResult>0)){
                throw new Exception("결제 이력 삽입 실패");
            }
            productMap.clear();
            paymentHistory.clear();
        }
        ShippingTrackInfoDTO shippingTrackInfo = new ShippingTrackInfoDTO();
        shippingTrackInfo.setRefMemberNo(refMemberNo);
        shippingTrackInfo.setRequest(pay.getRequest());
        shippingTrackInfo.setRefPaymentNo(refPaymentNo);
        int trackInfoResult = mapper.inserttrackInfo(shippingTrackInfo);
        if(!(trackInfoResult>0)){
            throw new Exception("배송정보 삽입 실패");
        }
        return true;
    }
}
