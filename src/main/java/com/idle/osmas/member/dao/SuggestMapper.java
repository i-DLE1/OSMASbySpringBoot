package com.idle.osmas.member.dao;

import com.idle.osmas.member.dto.ReviewsDTO;
import com.idle.osmas.member.dto.SuggestsDTO;
import com.idle.osmas.member.paging.SelectCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SuggestMapper {
    int selectTotalCount(Map<String, String> searchMap);
    List<SuggestsDTO> selectSuggestList(SelectCriteria selectCriteria);

}
