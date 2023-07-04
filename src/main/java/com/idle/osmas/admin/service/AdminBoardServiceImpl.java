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
    public List<AdminBoardDTO> getAllAdminBoards(){
        return adminBoardMapper.getAllAdminBoards();
    }

    @Override
    public List<AdminBoardDTO> getAdminNotice(){
        return adminBoardMapper.getAdminNotice();
    }

    @Override
    public List<AdminBoardDTO> getAdminEvent(){
        return adminBoardMapper.getAdminEvent();
    }

    @Override
    public List<AdminBoardDTO> getAdminArticle(){
        return adminBoardMapper.getAdminArticle();
    }


    @Override
    public List<AdminBoardFileDTO> adminBoardFileList() {
        return adminBoardMapper.getAllAdminBoardFiles();
    }

}
