package com.idle.osmas.seller.dao;

import com.idle.osmas.seller.dto.ProjectFAQDTO;
import com.idle.osmas.seller.dto.ProjectFileDTO;
import com.idle.osmas.seller.dto.ProjectFileType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectFileMapper {

    ProjectFileDTO selectProjectFileByNo(int no);

    List<ProjectFileDTO> selectProjectFileListByProjectNo(int projectNo, int userNo);

    int insertProjectFile(ProjectFileType fileType, String originFile,
                          String savedFile, String deleteYN,
                          int projectNo);

    ProjectFileDTO selectByProjectSaveFileName(String saveFileName);

    int deleteProjectFilesByProjectNo(int projectNo);

    int updateNonAvailableProjectFileByFileType(int projectNo, ProjectFileType fileType);

    int updateNonAvailableProjectFileByChangeName(String changeName);


}
