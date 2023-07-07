package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
public interface SalesService {

    public List<SalesDTO> selectAllProject();
    public SalesDTO selectProjectByNo(int no);

    public List<OptionDTO> selectOptionByNo(int no);

    public CategoryDTO selectCategoryByNo(int no);

    public SellerRollDTO selectSellerRollByNo(int no);

    public List<QnaDTO> selectQnaListByNo(int no);

    public List<FaqDTO> selectFaqListByNo(int no);

    public List<NewInfoDTO> selectNewInfoListByNo(int no);

    int insertNewQna(QnaDTO qnaDTO);

}
