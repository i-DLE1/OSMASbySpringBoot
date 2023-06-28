package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dao.SalesMapper;
import com.idle.osmas.seller.dto.SalesDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SalesServiceImpl implements SalesService{

    private final SalesMapper salesMapper;

    public SalesServiceImpl(SalesMapper salesMapper) {
        this.salesMapper = salesMapper;
    }

    @Override
    public List<SalesDTO> selectAllProject() {
        return salesMapper.selectAllProject();
    }

    @Override
    public SalesDTO selectProjectByNo(int no) {
        return salesMapper.selectProjectByNo(no);
    }


}
