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

    @Override
    @Transactional
    public int sendProposals(int proposalNo, String reasonText) {
        int result1 = gettingProposalsMapper.sendProposals(proposalNo, reasonText);
        System.out.println("result1 : " + result1);
        int result2 =gettingProposalsMapper.proposalsStatus(proposalNo);
        System.out.println("result2 : " + result2);

        int result = 0;

        // sellerRoleMapper의 작업 수행 후 결과를 result 변수에 할당
        if ((result1 > 0) && (result2 > 0)) {
            result = 1;
        }
        return result;
    }
}
