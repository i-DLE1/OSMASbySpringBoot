package com.idle.osmas.seller.dao;

import com.idle.osmas.seller.dto.ProjectFAQDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectFAQMapper {
    List<ProjectFAQDTO> selectAllProjectFAQ(int projectNo); // 프로젝트 faq 조회

    ProjectFAQDTO selectById(int projectFaqNo);

    int insertProjectFAQ(int projectNo, ProjectFAQDTO projectFAQ);

    int updateProjectFAQ(int projectFaqNo, ProjectFAQDTO projectFAQ);

}
