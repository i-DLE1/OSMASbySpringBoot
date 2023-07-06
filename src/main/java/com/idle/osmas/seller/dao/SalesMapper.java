package com.idle.osmas.seller.dao;

import com.idle.osmas.seller.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface SalesMapper {

    List<SalesDTO> selectAllProject();
    SalesDTO selectProjectByNo(int no);
    List<OptionDTO> selectOptionByNo(int no);
    CategoryDTO selectCategoryByNo(int no);

    SellerRollDTO selectSellerRollByNo(int no);

    List<QnaDTO> selectQnaListByNo(int no);

    List<FaqDTO> selectFaqListByNo(int no);

    List<NewInfoDTO> selectNewInfoListByNo(int no);

    int insertNewQna(QnaDTO qnaDTO);
}
