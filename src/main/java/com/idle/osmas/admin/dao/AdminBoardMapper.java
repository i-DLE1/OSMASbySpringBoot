package com.idle.osmas.admin.dao;

import com.idle.osmas.admin.dto.AdminBoardDTO;
import com.idle.osmas.admin.dto.AdminBoardFileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminBoardMapper {

    List<AdminBoardDTO> getAllAdminBoards();  // 관리자 게시판의 모든 게시글 조회

    List<AdminBoardDTO> getAdminBoardsByCategory(@Param("category") String category);
    // 파라미터로 카테고리를 받아 해당 카테고리의 게시글 조회

    List<AdminBoardFileDTO> getAllAdminBoardFiles();

}
