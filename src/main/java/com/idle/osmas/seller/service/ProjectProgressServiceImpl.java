package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dao.ProjectProgressMapper;
import com.idle.osmas.seller.dto.ProjectDTO;
import com.idle.osmas.seller.dto.ProjectProgressDTO;
import com.idle.osmas.seller.dto.ProjectProgressStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectProgressServiceImpl implements ProjectProgressService {

    private final ProjectProgressMapper projectProgressMapper;

    public ProjectProgressServiceImpl(ProjectProgressMapper projectProgressMapper) {
        this.projectProgressMapper = projectProgressMapper;
    }

    @Override
    public int insertProjectProgressStatus(ProjectProgressDTO projectProgress) {
        int result = projectProgressMapper.insertProjectProgressStatus(projectProgress);
        System.out.println("result = " + result);

        return result;
    }

    /**
     *
     * @param projectNo
     * @param status available null
     * @return
     */
    @Override
    public ProjectProgressDTO progressLastStatusById(int projectNo, ProjectProgressStatus status) {
        ProjectProgressDTO lastPorjectStatus = projectProgressMapper.progressLastStatusById(projectNo, status);

        if(lastPorjectStatus.getStatus().equals(ProjectProgressStatus.REJECTED)){
            return projectProgressMapper.progressLastStatusById(projectNo, status) ;
        }else {
            return lastPorjectStatus;
        }
    }

    @Override
    @Transactional
    public int deleteProjectProgressByProjectNo(int projectNo) {
        return projectProgressMapper.deleteProjectProgressByProjectNo(projectNo);
    }
}
