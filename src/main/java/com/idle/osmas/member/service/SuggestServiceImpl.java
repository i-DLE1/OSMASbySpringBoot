package com.idle.osmas.member.service;

import com.idle.osmas.member.dao.SuggestMapper;
import com.idle.osmas.member.dto.SuggestsDTO;
import com.idle.osmas.member.paging.SelectCriteria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SuggestServiceImpl implements SuggestService{

    private final SuggestMapper mapper;

    public SuggestServiceImpl(SuggestMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public int selectTotalCount(Map<String, String> searchMap) {
        return mapper.selectTotalCount(searchMap);
    }

    @Override
    public List<SuggestsDTO> selectSuggestList(SelectCriteria selectCriteria) {
        return mapper.selectSuggestList(selectCriteria);
    }
}
