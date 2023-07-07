package com.idle.osmas.admin.service;

import com.idle.osmas.admin.dao.TermsMapper;
import com.idle.osmas.admin.dto.TermsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TermsServiceImpl implements TermsService{

    private final TermsMapper termsMapper;


    public TermsServiceImpl(TermsMapper termsMapper) {
        this.termsMapper = termsMapper;
    }

    //약관 insert
    @Override
    public int termsInputGo(String title, String content) {
        int result = termsMapper.termsInputGo(title, content);


        if (result > 0) {
            result = 1;
        }
        return result;
    }

    //이용약관
    @Override
    public List<TermsDTO> userTermsGet() {
        return termsMapper.userTermsGet();
    }

    //개인정보 처리방침
    @Override
    public List<TermsDTO> personalTermsGet() {
        return termsMapper.personalTermsGet();
    }

    //프로젝트 심사기준
    @Override
    public List<TermsDTO> projectTermsGet() {
        return termsMapper.projectTermsGet();
    }
}
