package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dto.ProjectFileDTO;
import com.idle.osmas.seller.dto.ProjectFileType;

import java.util.List;

public interface ProjectFileService {

    ProjectFileDTO selectProjectFileByNo(int no);

    List<ProjectFileDTO> selectProjectFileListByProjectNo(int projectNo, String userId);

    ProjectFileDTO selectByProjectSaveFileName(String saveFileName);
    int insertProjectFile(ProjectFileType fileType, String originFile, String savedFile, String deleteYN, int projectNo);

    int updateNonAvailableProjectFileByFileType(int projectNo, ProjectFileType fileType);

    int updateNonAvailableProjectFileByChangeName(String changeName);
}