package com.idle.osmas.admin.dao;

import com.idle.osmas.admin.dto.UserManagementDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserManagementMapper {
    List<UserManagementDTO> selectAllMembers();
    }

