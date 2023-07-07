package com.idle.osmas.admin.dao;

import com.idle.osmas.admin.dto.TermsDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TermsMapper {
    int termsInputGo(String title, String content);

    List<TermsDTO> userTermsGet();

    List<TermsDTO> personalTermsGet();

    List<TermsDTO> projectTermsGet();
}
