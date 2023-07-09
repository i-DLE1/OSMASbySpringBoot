package com.idle.osmas.seller.dao;

import com.idle.osmas.admin.dto.TermsDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectTermMapper {
    List<TermsDTO> selectTermListByNo();

}
