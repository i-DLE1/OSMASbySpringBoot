package com.idle.osmas.seller.dao;

import com.idle.osmas.seller.dto.ProjectDTO;
import com.idle.osmas.seller.dto.ProjectFAQDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProejctMapper {

    List<ProjectDTO> selectAllProject(); // 프로젝트 모두 조회

    List<ProjectDTO> selectAllByAccount(int refMemberNo); // 프로젝트 계정 no으로 조회

    ProjectDTO selectById(int no); // 프로젝트 no 조회

    int insertProject(ProjectDTO project); // 프로젝트 등록

    int updateProject(ProjectDTO project); // 임시저장 및 업데이트

}
