package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dto.ProjectQnADTO;

import java.util.List;
import java.util.Map;

public interface ProjectQnAService {

    int selectByListTypeAndSearchProjectQnACount(Map<String, Object> searchCriteria);
    List<ProjectQnADTO> selectByListTypeAndSearchProjectQnA(Map<String, Object> searchCriteria);

    ProjectQnADTO selectByQnANo(int no);

    int insertProjectQnAAnswer(Map<String, Object> insertData);

    int updateProjectQnAAnswer(Map<String, Object> updateData);

    int deleteProjectQnAByProjectNo(int projectNo);
}
