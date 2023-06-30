package com.idle.osmas.seller.dao;

import com.idle.osmas.seller.dto.CategoryDTO;
import com.idle.osmas.seller.dto.ProductDTO;
import com.idle.osmas.seller.dto.ProjectDTO;
import com.idle.osmas.seller.dto.ProjectFileType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface RegistProjectMapper {

    List<ProjectDTO> selectAllProject(); // 프로젝트 모두 조회

    List<ProjectDTO> selectAllByAccount(int refMemberNo); // 프로젝트 계정 no으로 조회

    ProjectDTO selectProjectById(int projectNo); // 프로젝트 no 조회

    int insertProject(ProjectDTO project); // 프로젝트 등록

    int updateProject(ProjectDTO project); // 임시저장 및 업데이트

//    List<CategoryDTO> selectAllCategory();
    List<CategoryDTO> selectByCategoryType(@Param("categoryNo") Integer categoryNo);

    ProjectDTO selectTemporaryByUserId(String userId);

    int temporaryInsertProject(ProjectDTO projectDTO);

    int temporaryInsertProjectProgress(ProjectDTO projectDTO);

    int temporaryInsertProjectProduct(ProductDTO product);

    int temporaryInsertProjectProductList(@Param("projectNo") int projectNo ,@Param("productNo") int productNo);

    int temporaryUpdateProjectProduct(ProductDTO product);

    ProductDTO selectProjectProduct(int productNo);

    int deleteProjectProduct(int productNo);

    int deleteProjectProductList(int productNo);

    int insertProjectFile(@Param("fileType") ProjectFileType fileType,
                          @Param("originFile") String originFile,
                          @Param("savedFile") String savedFile,
                          @Param("deleteYN") String deleteYN,
                          @Param("projectNo") int projectNo);


}
