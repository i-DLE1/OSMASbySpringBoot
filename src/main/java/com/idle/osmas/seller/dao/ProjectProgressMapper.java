package com.idle.osmas.seller.dao;

import com.idle.osmas.seller.dto.ProjectProgressDTO;
import com.idle.osmas.seller.dto.ProjectProgressStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectProgressMapper {

    ProjectProgressDTO progressLastStatusById(int projectNo, ProjectProgressStatus status);
    int insertProjectProgressStatus(ProjectProgressDTO projectProgress);

    int deleteProjectProgressByProjectNo(int projectNo);

}
