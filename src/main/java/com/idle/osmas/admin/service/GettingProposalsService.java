package com.idle.osmas.admin.service;

import com.idle.osmas.member.dto.SuggestDTO;

import java.util.List;

public interface GettingProposalsService {

    List<SuggestDTO> gettingProposalsAll();  //받은 제안 모두 확인
}
