package com.idle.osmas.seller.dao;

import com.idle.osmas.seller.dto.ProjectProgressDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductProgressMapper {

    List<ProjectProgressDTO> selectAllProjectProgressById(int projectNo); // 프로젝트 번호로 변경이력 조회

    ProjectProgressDTO LastSelectByProjectId(int projectNo);

    int insertProjectProgressById(ProjectProgressDTO projectProgressDTO, int projectNo);

//    int updateProjectProgressById(ProjectProgressDTO projectProgressDTO, int projectNo);



}
