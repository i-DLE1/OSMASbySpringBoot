package com.idle.osmas.admin.service;

import com.idle.osmas.admin.dto.AdminBoardDTO;
import com.idle.osmas.admin.dto.AdminBoardFileDTO;

import java.util.List;

public interface AdminBoardService {

    // 관리자 게시판의 모든 게시물 가져옴
    List<AdminBoardDTO> getAllAdminBoards();

    // 카테고리에 해당하는 게시물 가져옴
    List<AdminBoardDTO> getAdminNotice();

    List<AdminBoardDTO> getAdminEvent();

    List<AdminBoardDTO> getAdminArticle();

    List<AdminBoardFileDTO> adminBoardFileList();


}
