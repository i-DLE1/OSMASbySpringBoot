package com.idle.osmas.admin.service;

import com.idle.osmas.admin.dao.GettingProposalsMapper;
import com.idle.osmas.member.dto.SuggestDTO;
import org.springframework.stereotype.Service;

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
}
