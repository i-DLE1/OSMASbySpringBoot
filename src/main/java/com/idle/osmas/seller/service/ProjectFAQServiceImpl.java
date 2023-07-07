package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dao.ProjectFAQMapper;
import com.idle.osmas.seller.dto.ProjectFAQDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectFAQServiceImpl implements ProjectFAQService {

    private final ProjectFAQMapper projectFAQMapper;

    int result;

    public ProjectFAQServiceImpl(ProjectFAQMapper projectFAQMapper) {
        this.projectFAQMapper = projectFAQMapper;
    }

    @Override
    @Transactional
    public int insertProjectFAQ(int projectNo, ProjectFAQDTO projectFAQ) {
        return projectFAQMapper.insertProjectFAQ(projectNo, projectFAQ);
    }

    @Override
    @Transactional
    public int updateProjectFAQ(ProjectFAQDTO projectFAQ) {
        return projectFAQMapper.updateProjectFAQ(projectFAQ);
    }

    @Override
    @Transactional
    public int deleteProjectFAQ(List<ProjectFAQDTO> projectFAQList) {
        result = 0;
        int length = projectFAQList.size();
        projectFAQList.forEach(e->{
            projectFAQMapper.deleteProjectFAQ(e.getNo());
            result++;
        });

        if(result != length){
            return 0;
        }
        return 1;
    }

    @Override
    public List<ProjectFAQDTO> selectProjectFaqByProjectNo(int projectNo, int userNo) {
        return projectFAQMapper.selectProjectFaqByProjectNo(projectNo, userNo);
    }

    @Override
    @Transactional
    public int deleteProjectFaqByProjectNo(int projectNo) {
        return projectFAQMapper.deleteProjectFaqByProjectNo(projectNo);
    }
}
