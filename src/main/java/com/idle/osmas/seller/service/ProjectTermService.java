package com.idle.osmas.seller.service;

import com.idle.osmas.admin.dto.TermsDTO;

import java.util.List;

public interface ProjectTermService {
    List<TermsDTO> selectTermListByNo();
}
