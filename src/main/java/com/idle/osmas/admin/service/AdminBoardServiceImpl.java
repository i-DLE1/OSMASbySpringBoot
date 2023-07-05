package com.idle.osmas.admin.service;

import com.idle.osmas.admin.dao.AdminBoardMapper;
import com.idle.osmas.admin.dto.AdminBoardDTO;
import com.idle.osmas.admin.dto.AdminBoardFileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
