package com.idle.osmas.seller.dao;

import com.idle.osmas.seller.dto.ProjectDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectMapper {

    Integer selectTemporaryProjectNoByUserId(String userId);

    Integer existProjectByProjectNo (int projectNo, String userId);

    ProjectDTO selectProjectInfoByProjectNo(int projectNo, String userId);

    ProjectDTO selectProjectByProjectNo(int projectNo, String userId);

    int insertTemporaryProject(ProjectDTO projectDTO);

    int updateProjectInfo(ProjectDTO projectDTO);

    int updateProjectContent(int no, String content);

    int deleteProjectByProjectNo(int projectNo);


}
