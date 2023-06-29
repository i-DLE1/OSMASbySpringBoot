package com.idle.osmas.seller.dao;

import com.idle.osmas.seller.dto.ProjectProgressDTO;
import com.idle.osmas.seller.dto.ProjectProgressStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectProgressMapper {

    List<ProjectProgressDTO> selectAllProjectProgressById(int projectNo); // 프로젝트 번호로 변경이력 조회

    ProjectProgressDTO lastSelectByProjectId(int projectNo);

    ProjectProgressDTO progressLastStatusById(@Param("projectNo") int projectNo, @Param("status") ProjectProgressStatus status);
    int insertProjectProgressStatus(ProjectProgressDTO projectProgress);

}
