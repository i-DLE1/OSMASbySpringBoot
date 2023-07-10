package com.idle.osmas.seller.service;

import com.idle.osmas.admin.dto.TermsDTO;
import com.idle.osmas.seller.dao.ProjectTermMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service()
public class ProjectTermServiceImpl implements ProjectTermService {

    private final ProjectTermMapper termsMapper;

    public ProjectTermServiceImpl(ProjectTermMapper termsMapper) {
        this.termsMapper = termsMapper;
    }

    @Override
    public List<TermsDTO> selectTermListByNo() {
        return termsMapper.selectTermListByNo();
    }
}
