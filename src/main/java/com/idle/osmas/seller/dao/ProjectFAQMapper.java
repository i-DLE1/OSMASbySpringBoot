package com.idle.osmas.seller.dao;

import com.idle.osmas.seller.dto.ProjectFAQDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectFAQMapper {

    List<ProjectFAQDTO> selectProjectFaqByProjectNo(int projectNo, int userNo);

    int insertProjectFAQ(int projectRefNo, ProjectFAQDTO projectFaq);
    int updateProjectFAQ(ProjectFAQDTO projectFAQ);

    int deleteProjectFAQ(int no);

    int deleteProjectFaqByProjectNo(int projectNo);

}
