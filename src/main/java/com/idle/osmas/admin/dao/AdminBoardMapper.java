package com.idle.osmas.admin.dao;

import com.idle.osmas.admin.dto.AdminBoardDTO;
import com.idle.osmas.admin.dto.AdminBoardFileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminBoardMapper {

    List<AdminBoardDTO> getAllAdminBoards(String boardtype);  // 관리자 게시판의 모든 게시글 조회

    List<AdminBoardDTO> getAdminNotice();

    List<AdminBoardDTO> getAdminEvent();

    List<AdminBoardDTO> getAdminArticle();

    List<AdminBoardFileDTO> getAllAdminBoardFiles();

    AdminBoardDTO getAdminBoardByNo(@Param("no") int no); // 공지사항 번호로 해당 공지사항 조회

    int registNotice(AdminBoardDTO adminBoardDTO);

    int deleteNotice(@Param("no") int no);

    int updateNotice(Map<String, Object> paramMap);

}