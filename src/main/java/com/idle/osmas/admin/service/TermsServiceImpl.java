package com.idle.osmas.admin.service;

import com.idle.osmas.admin.dao.TermsMapper;
import org.springframework.stereotype.Service;

@Service
public class TermsServiceImpl implements TermsService{

    private final TermsMapper termsMapper;


    public TermsServiceImpl(TermsMapper termsMapper) {
        this.termsMapper = termsMapper;
    }

    @Override
    public int termsInputGo(String title, String content) {
        int result = termsMapper.termsInputGo(title, content);


        if (result > 0) {
            result = 1;
        }
        return result;
    }
}
