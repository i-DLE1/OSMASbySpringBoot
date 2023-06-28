package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dao.ProjectMapper;
import com.idle.osmas.seller.dao.SellerPageMapper;
import com.idle.osmas.seller.dto.ProjectDTO;
import com.idle.osmas.seller.dto.ProjectQnAAnswerDTO;
import com.idle.osmas.seller.dto.ProjectQnADTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class SellerPageServiceImpl implements SellerPageService {

    private final SellerPageMapper sellerPageMapper;

    public SellerPageServiceImpl(SellerPageMapper sellerPageMapper) {
        this.sellerPageMapper = sellerPageMapper;
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
}
