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
    public int insertProjectFAQ(List<ProjectFAQDTO> projectFAQList) {
        if(projectFAQList.size() > 0 ) return projectFAQMapper.insertProjectFAQ(projectFAQList);
        return 1;
    }

    @Override
    @Transactional
    public int updateProjectFAQ(List<ProjectFAQDTO> projectFAQList) {
        if(projectFAQList.size() > 0) return projectFAQMapper.updateProjectFAQ(projectFAQList);
        return 1;
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
