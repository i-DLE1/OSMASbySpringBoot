package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dao.RegistProjectMapper;
import com.idle.osmas.seller.dto.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class RegsitProjectServiceImpl implements RegistProjectService {


    private final RegistProjectMapper registProjectMapper;
    private int result;

    @Override
    public boolean existProjectByProjectNo(int projectNo,String userId) {
        return registProjectMapper.existProjectByProjectNo(projectNo, userId);
    }

    public RegsitProjectServiceImpl(RegistProjectMapper registProjectMapper) {
        this.registProjectMapper = registProjectMapper;
    }

    @Override
    public List<CategoryDTO> selectByCategoryType(Integer categoryNo) {
        return registProjectMapper.selectByCategoryType(categoryNo);
    }

    @Override
    public int selectMemberNoById(String userId) {
        return registProjectMapper.selectMemberNoById(userId);
    }

    @Override
    public int insertProject(ProjectDTO project) {
        int projectResult = registProjectMapper.insertProject(project);
        System.out.println("projectResult = " + project);
        int progressResult = registProjectMapper.insertProjectProgress(project);
        System.out.println("result = " + project.getNo());

        if (projectResult + progressResult == 2) return 1;
        else return 2;
    }

    @Override
    public int insertTemporaryProject(ProjectDTO projectDTO) {
        return registProjectMapper.insertTemporaryProject(projectDTO);
    }

    @Override
    public ProjectDTO selectProjectInfoByProjectNo(int projectNo, String userId) {

        return registProjectMapper.selectProjectInfoByProjectNo(projectNo, userId);
    }

    @Override
    public int insertProjectProduct(List<ProductDTO> productList, String userId, int projectNo) {
        productList.forEach( e ->{
            if(e.getNo() == 0){
                registProjectMapper.insertProjectProduct(e);
                registProjectMapper.insertProjectProductList(projectNo, e.getNo());
            }else {
                registProjectMapper.updateProjectProduct(e);
            }
        });

        return 1;
    }

    @Override
    public int deleteProjectProduct(List<ProductDTO> productList) {
        productList.forEach(e->{
            registProjectMapper.deleteProjectProductList(e.getNo());
            registProjectMapper.deleteProjectProduct(e.getNo());
        });
        return 0;
    }

    @Override
    public int insertProjectFile(ProjectFileType fileType, String originFile, String savedFile, String deleteYN, int projectNo) {

        return registProjectMapper.insertProjectFile(fileType, originFile, savedFile, deleteYN, projectNo);
    }


    @Override
    public ProjectFileDTO selectByProjectSaveFileName(String saveFileName, int projectNo) {
        return registProjectMapper.selectByProjectSaveFileName(saveFileName, projectNo);
    }

    @Override
    public Integer selectTemporaryProjectNoByUserId(String userId) {
        return registProjectMapper.selectTemporaryProjectNoByUserId(userId);
    }

    @Override
    public List<ProductDTO> selectProductListByProjectNo(int projectNo, String userId) {
        return registProjectMapper.selectProductListByProjectNo(projectNo, userId);
    }

    @Override
    public List<ProjectFileDTO> selectProjectFileListByProjectNo(int projectNo, String userId) {
        return registProjectMapper.selectProjectFileListByProjectNo(projectNo, userId);
    }

    @Override
    public int updateProjectContent(ProjectDTO project, Integer no) {

        return registProjectMapper.updateProjectContent(no, project.getContent());
    }

    @Override
    public int insertProjectFAQ(int projectNo, ProjectFAQDTO projectFAQ) {
        return registProjectMapper.insertProjectFAQ(projectNo, projectFAQ);
    }

    @Override
    public int updateProjectFAQ(ProjectFAQDTO projectFAQ) {
        return registProjectMapper.updateProjectFAQ(projectFAQ);
    }

    @Override
    public int deleteProjectFAQ(List<ProjectFAQDTO> projectFAQList) {
        result = 0;
        int length = projectFAQList.size();
        projectFAQList.forEach(e->{
            registProjectMapper.deleteProjectFAQ(e.getNo());
            result++;
        });

        if(result != length){
            return 0;
        }
        return 1;
    }

    @Override
    public List<ProjectFAQDTO> selectProjectFaqByProjectNo(int projectNo, String userId) {
        return registProjectMapper.selectProjectFaqByProjectNo(projectNo, userId);
    }

    @Override
    public List<ProjectNewsDTO> selectProjectNewsListByProjectNo(int projectNo, String userId) {
        return registProjectMapper.selectProjectNewsListByProjectNo(projectNo, userId);
    }

    @Override
    public ProjectNewsDTO selectProjectNewsByProjectNewsNo(int no, String userId) {
        return registProjectMapper.selectProjectNewsByProjectNewsNo(no, userId);
    }

    @Override
    public int insertProjectNews(int projectNo, ProjectNewsDTO projectNews) {
        return registProjectMapper.insertProjectNews(projectNo, projectNews);
    }

    @Override
    public int deleteProjectNews(int projectNewsNo) {
        return registProjectMapper.deleteProjectNews(projectNewsNo);
    }

    @Override
    public int updateProjectNews(ProjectNewsDTO projectNews) {
        return registProjectMapper.updateProjectNews(projectNews);
    }

    @Override
    public ProjectDTO selectProjectByProjectNo(int projectNo, String userId) {
        return registProjectMapper.selectProjectByProjectNo(projectNo, userId);
    }
}
