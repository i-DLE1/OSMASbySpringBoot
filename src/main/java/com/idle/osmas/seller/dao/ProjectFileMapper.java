package com.idle.osmas.seller.dao;

import com.idle.osmas.seller.dto.ProjectFAQDTO;
import com.idle.osmas.seller.dto.ProjectFileDTO;
import com.idle.osmas.seller.dto.ProjectFileType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectFileMapper {

    List<ProjectFileDTO> selectAllProjectFile(int projectNo);

    List<ProjectFileDTO> selectByType(int projectNo, ProjectFileType projectFileType);

    int insertProjectFile(ProjectFileDTO projectFile);




}
