package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dto.ProjectDTO;
import com.idle.osmas.seller.dto.ProjectQnAAnswerDTO;
import com.idle.osmas.seller.dto.ProjectQnADTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SellerPageService {

    List<ProjectDTO> selectByProgressAndSearchProjectManagement(Map<String, Object> searchCriteria);
    List<ProjectQnADTO> selectByListTypeAndSearchProjectQnA(Map<String, Object> searchCriteria);

    ProjectQnADTO selectByQnANo(int no);

    int insertProjectQnAAnswer(Map<String, Object> insertData);

    int updateProjectQnAAnswer(Map<String, Object> updateData);

}