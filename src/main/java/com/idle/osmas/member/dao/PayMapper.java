package com.idle.osmas.member.dao;

import com.idle.osmas.member.dto.*;
import com.idle.osmas.seller.dto.ProjectDTO;
import com.idle.osmas.seller.dto.ProjectFileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface PayMapper {

    MemberDTO selectMemberById(String id);
    AddressDTO selectAddressByNo(int no);

    int insertAddress(AddressDTO address);
    int modAddress(AddressDTO address);

    ProductsDTO selectProduct(int productNo);

    PayDTO selectPay(int no);

    int selectProductListNo(Map<String,Integer> productMap);

    int insertSponsoredProject(SponsoredProjectDTO sponsoredProject);

    int insertPayment(PaymentDTO payment);

    int insertPaymentHistory(Map<String,Integer> paymentHistory);

    int inserttrackInfo(ShippingTrackInfoDTO shippingTrackInfo);

    Long selectCurrentAmount(int projectNo);

    int modAmountPrj(ProjectDTO prj);

    ProjectFileDTO selectProjectFileDTO(int no);

    int insertDeleveryStatus(int refShippingTrackInfoNo);
}

