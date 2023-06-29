package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dao.ProjectProgressMapper;
import com.idle.osmas.seller.dao.RegistProjectMapper;
import com.idle.osmas.seller.dao.SellerPageMapper;
import com.idle.osmas.seller.dto.ProjectDTO;
import com.idle.osmas.seller.dto.ProjectQnADTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SellerPageServiceImpl implements SellerPageService {

    private final SellerPageMapper sellerPageMapper;

    private final ProjectProgressMapper projectProgressMapper;

    public SellerPageServiceImpl(SellerPageMapper sellerPageMapper, ProjectProgressMapper projectProgressMapper) {
        this.sellerPageMapper = sellerPageMapper;
        this.projectProgressMapper = projectProgressMapper;
    }

    @Override
    public List<ProjectDTO> selectByProgressAndSearchProjectManagement(Map<String, Object> searchCriteria) {
        return sellerPageMapper.selectByProgressAndSearchProjectManagement(searchCriteria);
    }

    @Override
    public List<ProjectQnADTO> selectByListTypeAndSearchProjectQnA(Map<String, Object> searchCriteria) {

        return sellerPageMapper.selectByListTypeAndSearchProjectQnA(searchCriteria);
    }

    @Override
    public ProjectQnADTO selectByQnANo(int no) {
        return sellerPageMapper.selectByQnANo(no);
    }

    @Override
    public int insertProjectQnAAnswer(Map<String, Object> insertData) {

        return sellerPageMapper.insertProjectQnAAnswer(insertData);
    }

    @Override
    public int updateProjectQnAAnswer(Map<String, Object> updateData){
        return sellerPageMapper.updateProjectQnAAnswer(updateData);
    }

    @Override
    public ProjectDTO selectByProjectId(int no) {

        return sellerPageMapper.selectByProjectId(no);
    }

}
