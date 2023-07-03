package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dao.ProjectFileMapper;
import com.idle.osmas.seller.dao.ProjectMapper;
import com.idle.osmas.seller.dto.ProjectFileDTO;
import com.idle.osmas.seller.dto.ProjectFileType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectFileServiceImpl implements  ProjectFileService{

    private final ProjectFileMapper projectFileMapper;

    public ProjectFileServiceImpl(ProjectFileMapper projectFileMapper) {
        this.projectFileMapper = projectFileMapper;
    }

    @Override
    public List<ProjectFileDTO> selectProjectFileListByProjectNo(int projectNo, String userId) {
        return projectFileMapper.selectProjectFileListByProjectNo(projectNo, userId);
    }

    @Override
    public ProjectFileDTO selectByProjectSaveFileName(String saveFileName) {
        return projectFileMapper.selectByProjectSaveFileName(saveFileName);
    }

    @Override
    public int insertProjectFile(ProjectFileType fileType, String originFile, String savedFile, String deleteYN, int projectNo) {
        return projectFileMapper.insertProjectFile(fileType, originFile, savedFile, deleteYN, projectNo);
    }
}
