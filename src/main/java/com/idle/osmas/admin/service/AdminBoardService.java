package com.idle.osmas.admin.service;

import com.idle.osmas.admin.dto.AdminBoardDTO;
import com.idle.osmas.admin.dto.AdminBoardFileDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface AdminBoardService {

    // 관리자 게시판의 모든 게시물 가져옴
    List<AdminBoardDTO> getAllAdminBoards(String boardtype);

    // 카테고리에 해당하는 게시물 가져옴
    List<AdminBoardDTO> getAdminNotice();

    List<AdminBoardDTO> getAdminEvent();

    List<AdminBoardDTO> getAdminArticle();

    List<AdminBoardFileDTO> adminBoardFileList();

    AdminBoardDTO getAdminBoardByNo(int no);


    int registBoard(AdminBoardDTO board);

    int deleteAdminBoard(int no);

    @Transactional
    int updateAdminBoard(Map<String, Object> paramMap);
}
