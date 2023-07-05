package com.idle.osmas.seller.dao;

import com.idle.osmas.seller.dto.ProjectDTO;
import com.idle.osmas.seller.dto.ProjectProgressDTO;
import com.idle.osmas.seller.dto.ProjectQnAAnswerDTO;
import com.idle.osmas.seller.dto.ProjectQnADTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface SellerPageMapper {

    int selectByProgressAndSearchProjectManagementCount(Map<String, Object> searchCriteria);
    List<ProjectDTO> selectByProgressAndSearchProjectManagement(Map<String, Object> searchCriteria);

    int selectByListTypeAndSearchProjectQnACount(Map<String, Object> searchCriteria);
    List<ProjectQnADTO> selectByListTypeAndSearchProjectQnA(Map<String, Object> searchCriteria);

    ProjectQnADTO selectByQnANo(int no);

    int insertProjectQnAAnswer(Map<String, Object> insertData);
    int updateProjectQnAAnswer(Map<String, Object> updateData);

    ProjectDTO selectByProjectId(int no, int userNo);

}
