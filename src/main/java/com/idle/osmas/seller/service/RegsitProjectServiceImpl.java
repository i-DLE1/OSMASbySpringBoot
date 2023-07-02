package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dao.RegistProjectMapper;
import com.idle.osmas.seller.dto.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class RegsitProjectServiceImpl implements RegistProjectService {

    private final RegistProjectMapper registProjectMapper;

    public RegsitProjectServiceImpl(RegistProjectMapper registProjectMapper) {
        this.registProjectMapper = registProjectMapper;
    }

    @Override
    public int selectMemberNoById(String userId) {
        return registProjectMapper.selectMemberNoById(userId);
    }

}
