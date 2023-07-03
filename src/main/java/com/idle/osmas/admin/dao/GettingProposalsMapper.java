package com.idle.osmas.admin.dao;

import com.idle.osmas.member.dto.SuggestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GettingProposalsMapper {

    List<SuggestDTO> gettingProposalsAll();  //받은 제안 모두 확인

   // int sendProposals(String proposalNo, String reasonText); //제안 알림 보내기
}
