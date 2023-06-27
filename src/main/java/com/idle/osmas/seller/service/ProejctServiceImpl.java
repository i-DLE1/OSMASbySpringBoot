package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dao.ProjectMapper;
import com.idle.osmas.seller.dto.ProjectDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProejctServiceImpl implements ProejctService {


    private final ProjectMapper projectMapper;

    public ProejctServiceImpl(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;

    }

    @Override
    public List<ProjectDTO> selectAllProject() {
        return projectMapper.selectAllProject();
    }

    @Override
    public List<ProjectDTO> selectAllByAccount(int refMemberNo) {
        return projectMapper.selectAllByAccount(refMemberNo);
    }
}
