package com.idle.osmas.admin.service;

import com.idle.osmas.admin.dao.UserManagementMapper;
import com.idle.osmas.admin.dto.UserManagementDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManagementServiceImpl implements UserManagementService {

    private final UserManagementMapper userManagementMapper;

    public UserManagementServiceImpl(UserManagementMapper userManagementMapper) {
        this.userManagementMapper = userManagementMapper;
    }

    @Override
    public List<UserManagementDTO> getAllUsers() {
        return userManagementMapper.selectAllMembers();
    }
}
