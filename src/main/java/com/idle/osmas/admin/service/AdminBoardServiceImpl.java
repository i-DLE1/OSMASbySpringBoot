package com.idle.osmas.admin.service;

import com.idle.osmas.admin.dao.AdminBoardMapper;
import com.idle.osmas.admin.dto.AdminBoardDTO;
import com.idle.osmas.admin.dto.AdminBoardFileDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
    public List<AdminBoardDTO> getAdminBoardsByCategory(String category) {
        return adminBoardMapper.getAdminBoardsByCategory(category);
    }

    @Override
    public List<AdminBoardFileDTO> adminBoardFileList() {
        return adminBoardMapper.getAllAdminBoardFiles();
    }
}
