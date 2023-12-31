package com.idle.osmas.admin.service;

import com.idle.osmas.admin.dao.AdminBoardMapper;
import com.idle.osmas.admin.dto.AdminBoardDTO;
import com.idle.osmas.admin.dto.AdminBoardFileDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class AdminBoardServiceImpl implements AdminBoardService {

    private final AdminBoardMapper adminBoardMapper;

    public AdminBoardServiceImpl(AdminBoardMapper adminBoardMapper) {
        this.adminBoardMapper = adminBoardMapper;
    }

    @Override
    public List<AdminBoardDTO> getAllAdminBoards(String boardtype) {
        return adminBoardMapper.getAllAdminBoards(boardtype);
    }

    @Override
    public List<AdminBoardDTO> getAdminNotice() {
        return adminBoardMapper.getAdminNotice();
    }

    @Override
    public List<AdminBoardDTO> getAdminEvent() {
        return adminBoardMapper.getAdminEvent();
    }

    @Override
    public List<AdminBoardDTO> getAdminArticle() {
        return adminBoardMapper.getAdminArticle();
    }

    @Override
    public List<AdminBoardFileDTO> adminBoardFileList() {
        return adminBoardMapper.getAllAdminBoardFiles();
    }

    @Override
    public AdminBoardDTO getAdminBoardByNo(int no) {
        // adminBoardMapper를 이용하여 no에 해당하는 공지사항의 정보를 조회한 후 리턴합니다.
        return adminBoardMapper.getAdminBoardByNo(no);
    }

    // 게시글 등록용 메소드
    @Override
    @Transactional
    public int registBoard(AdminBoardDTO adminBoard) {
        return adminBoardMapper.registNotice(adminBoard);
    }

    // 공지사항 삭제 메소드 구현
    @Override
    @Transactional
    public int deleteAdminBoard(int no) {
        return adminBoardMapper.deleteNotice(no);
    }

    @Override
    @Transactional
    public int updateAdminBoard(Map<String, Object> paramMap) {
        return adminBoardMapper.updateNotice(paramMap);
    }

}