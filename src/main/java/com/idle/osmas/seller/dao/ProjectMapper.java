package com.idle.osmas.seller.dao;

import com.idle.osmas.seller.dto.ProjectDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProjectMapper {

    Integer selectTemporaryProjectNoByUserId(String userId);

    Integer existProjectByProjectNo (int projectNo, String userId);

    ProjectDTO selectProjectInfoByProjectNo(int projectNo, String userId);

    ProjectDTO selectProjectByProjectNo(int projectNo, String userId);


    List<ProjectDTO> selectProjectByCategory(Map<String, Object> params);

    int insertTemporaryProject(ProjectDTO projectDTO);

    int updateProjectInfo(ProjectDTO projectDTO);

    int updateProjectContent(int no, String content);

    int deleteProjectByProjectNo(int projectNo);


}
