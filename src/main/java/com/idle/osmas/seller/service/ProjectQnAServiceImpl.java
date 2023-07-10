package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dao.ProjectQnAMapper;
import com.idle.osmas.seller.dto.ProjectQnADTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ProjectQnAServiceImpl implements ProjectQnAService {

    private final ProjectQnAMapper projectQnAMapper;

    public ProjectQnAServiceImpl(ProjectQnAMapper projectQnAMapper) {
        this.projectQnAMapper = projectQnAMapper;
    }

    @Override
    public int selectByListTypeAndSearchProjectQnACount(Map<String, Object> searchCriteria) {
        return projectQnAMapper.selectByListTypeAndSearchProjectQnACount(searchCriteria);
    }

    @Override
    public List<ProjectQnADTO> selectByListTypeAndSearchProjectQnA(Map<String, Object> searchCriteria) {
        return projectQnAMapper.selectByListTypeAndSearchProjectQnA(searchCriteria);
    }

    @Override
    public ProjectQnADTO selectByQnANo(int no) {
        return projectQnAMapper.selectByQnANo(no);
    }

    @Override
    @Transactional
    public int insertProjectQnAAnswer(Map<String, Object> insertData) {
        return projectQnAMapper.insertProjectQnAAnswer(insertData);
    }

    @Override
    @Transactional
    public int updateProjectQnAAnswer(Map<String, Object> updateData) {
        return projectQnAMapper.updateProjectQnAAnswer(updateData);
    }

    @Override
    @Transactional
    public int deleteProjectQnAByProjectNo(int projectNo) {
        return projectQnAMapper.deleteProjectQnAByProjectNo(projectNo);
    }
}
