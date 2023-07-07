package com.idle.osmas.admin.service;

import com.idle.osmas.admin.dto.TermsDTO;

import java.util.List;

public interface TermsService {
    int termsInputGo(String title, String content);

    List<TermsDTO> userTermsGet();

    List<TermsDTO> personalTermsGet();

    List<TermsDTO> projectTermsGet();
}
