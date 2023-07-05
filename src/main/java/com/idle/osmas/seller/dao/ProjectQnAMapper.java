package com.idle.osmas.seller.dao;

import com.idle.osmas.seller.dto.ProjectQnAAnswerDTO;
import com.idle.osmas.seller.dto.ProjectQnADTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProjectQnAMapper {

    int selectByListTypeAndSearchProjectQnACount(Map<String, Object> searchCriteria);
    List<ProjectQnADTO> selectByListTypeAndSearchProjectQnA(Map<String, Object> searchCriteria);

    ProjectQnADTO selectByQnANo(int no);

    int insertProjectQnAAnswer(Map<String, Object> insertData);
    int updateProjectQnAAnswer(Map<String, Object> updateData);

    int deleteProjectQnAByProjectNo(int projectNo);


}
