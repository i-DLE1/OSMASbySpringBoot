package com.idle.osmas.admin.service;

import com.idle.osmas.admin.dao.TermsMapper;
import com.idle.osmas.admin.dto.TermsDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TermsServiceImpl implements TermsService{

    private final TermsMapper termsMapper;


    public TermsServiceImpl(TermsMapper termsMapper) {
        this.termsMapper = termsMapper;
    }

    //약관 insert
    @Override
    @Transactional
    public int termsInputGo(String title, String content) {
        int result1 = termsMapper.termsInputGo(title, content);
        int result2 = termsMapper.termsHistoryInputGo(content);

        int result = 0;

        if ((result1 > 0 && result2 > 0)) {
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

    //OSMAS 소개단
    @Override
    public List<TermsDTO> OSAMS() {
        return termsMapper.OSAMS();
    }

    //약관 수정
    @Override
    @Transactional
    public int termsEditGO(String title, String content) {
        int result1 = termsMapper.termsEditGO(title,content);
        int result2 = termsMapper.termsEditUpdate(title, content);

        int result = 0;

        if ((result1 > 0 && result2 > 0)) {
            result = 1;
        }
        return result;
    }

    @Override
    public Integer youAdmin(String userID) {
        Integer result = termsMapper.youAdmin(userID);

        System.out.println("결과값 : " + result);

        if (result != null && result > 0) {
            return 1;
        }
        return 0;
    }
}
