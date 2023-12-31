package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dao.SalesMapper;
import com.idle.osmas.seller.dto.*;
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

    @Override
    public List<OptionDTO> selectOptionByNo(int no) {
        return salesMapper.selectOptionByNo(no);
    }
    @Override
    public CategoryDTO selectCategoryByNo(int no) {
        return salesMapper.selectCategoryByNo(no);
    }

    @Override
    public SellerRollDTO selectSellerRollByNo(int no) {
        return salesMapper.selectSellerRollByNo(no);
    }

    @Override
    public List<QnaDTO> selectQnaListByNo(int no) {
        return salesMapper.selectQnaListByNo(no);
    }

    @Override
    public List<FaqDTO> selectFaqListByNo(int no) {
        return salesMapper.selectFaqListByNo(no);
    }

    @Override
    public List<NewInfoDTO> selectNewInfoListByNo(int no) {
        return salesMapper.selectNewInfoListByNo(no);
    }

    @Override
    public List<ProjectFileDTO> selectprojectFileListByNo(int no) {
        return salesMapper.selectprojectFileListByNo(no);
    }

    @Override
    public int insertNewQna(QnaDTO qnaDTO) {
        return salesMapper.insertNewQna(qnaDTO);
    }


}
