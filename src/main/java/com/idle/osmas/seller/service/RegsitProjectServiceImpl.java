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

    public RegsitProjectServiceImpl(RegistProjectMapper registProjectMapper) {
        this.registProjectMapper = registProjectMapper;
    }

    @Override
    public List<CategoryDTO> selectByCategoryType(Integer categoryNo) {
        return registProjectMapper.selectByCategoryType(categoryNo);
    }

    @Override
    public int temporaryInsertProject(ProjectDTO project) {
        int projectResult = registProjectMapper.temporaryInsertProject(project);
        System.out.println("projectResult = " + project);
        int progressResult = registProjectMapper.temporaryInsertProjectProgress(project);
        System.out.println("result = " + project.getNo());

        if (projectResult + progressResult == 2) return 1;
        else return 2;
    }

    @Override
    public ProjectDTO selectTemporaryProjectInfoByProjectNo(int projectNo) {

        return registProjectMapper.selectTemporaryProjectInfoByProjectNo(projectNo);
    }

    @Override
    public int temporaryInsertProjectProduct(List<ProductDTO> productList, String userId) {
        productList.forEach( e ->{
            if(e.getNo() == 0){
                registProjectMapper.temporaryInsertProjectProduct(e);
                int projectNo =  registProjectMapper.selectTemporaryProjectNoByUserId(userId);
                registProjectMapper.temporaryInsertProjectProductList(projectNo, e.getNo());
            }else {
                registProjectMapper.temporaryUpdateProjectProduct(e);
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
    public int selectTemporaryProjectNoByUserId(String userId) {
        return registProjectMapper.selectTemporaryProjectNoByUserId(userId);
    }

    @Override
    public List<ProductDTO> selectTemporaryProductListByProjectNo(int projectNo) {
        return registProjectMapper.selectTemporaryProductListByProjectNo(projectNo);
    }

    @Override
    public List<ProjectFileDTO> selectTemporaryProjectFileListByProjectNo(int projectNo) {
        return registProjectMapper.selectTemporaryProjectFileListByProjectNo(projectNo);
    }

    @Override
    public int updateProjectContent(ProjectDTO project) {

        return registProjectMapper.updateProjectContent(53, project.getContent());
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
    public List<ProjectFAQDTO> selectTemporaryProjectFaqByProjectNo(int projectNo) {
        return registProjectMapper.selectTemporaryProjectFaqByProjectNo(projectNo);
    }

    @Override
    public List<ProjectNewsDTO> selectProjectNewsListByProjectNo(int projectNo) {
        return registProjectMapper.selectProjectNewsListByProjectNo(projectNo);
    }

    @Override
    public ProjectNewsDTO selectProjectNewsByProjectNewsNo(int no) {
        return registProjectMapper.selectProjectNewsByProjectNewsNo(no);
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
}
