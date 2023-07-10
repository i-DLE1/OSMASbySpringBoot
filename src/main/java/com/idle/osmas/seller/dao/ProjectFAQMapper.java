package com.idle.osmas.seller.dao;

import com.idle.osmas.seller.dto.ProjectFAQDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectFAQMapper {

    List<ProjectFAQDTO> selectProjectFaqByProjectNo(int projectNo, int userNo);

    int insertProjectFAQ(List<ProjectFAQDTO> projectFaqList);
    int updateProjectFAQ(List<ProjectFAQDTO> projectFaqList);

    int deleteProjectFAQ(int no);

    int deleteProjectFaqByProjectNo(int projectNo);

}
