package com.idle.osmas.admin.service;

import com.idle.osmas.admin.dao.GettingProposalsMapper;
import com.idle.osmas.member.dto.SuggestDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GettingProposalsServiceImpl implements GettingProposalsService{

    private final GettingProposalsMapper gettingProposalsMapper;

    public GettingProposalsServiceImpl(GettingProposalsMapper gettingProposalsMapper) {
        this.gettingProposalsMapper = gettingProposalsMapper;
    }

    @Override
    public List<SuggestDTO> gettingProposalsAll() {
        return gettingProposalsMapper.gettingProposalsAll();
    }

//    @Override
//    @Transactional
//    public int sendingProposals(String proposalNo, String reasonText) {
//        int result1 = GettingProposalsMapper.sendProposals(proposalNo, reasonText);
//        System.out.println("result1 : " + result1);
//
//        int result = 0;
//
//        // sellerRoleMapper의 작업 수행 후 결과를 result 변수에 할당
//        if ((result1 > 0)) {
//            result = 1;
//        }
//        return result;
//    }
}
