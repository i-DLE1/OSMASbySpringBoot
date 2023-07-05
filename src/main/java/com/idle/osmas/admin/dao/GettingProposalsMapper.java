package com.idle.osmas.admin.dao;

import com.idle.osmas.member.dto.SuggestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GettingProposalsMapper {

    List<SuggestDTO> gettingProposalsAll();  //받은 제안 모두 확인

    int sendProposals(int proposalNo, String reasonText); //제안 알림 보내기

    int proposalsStatus(int proposalNo); //제안 알림 보낸 후 상태값 바꾸기
}
