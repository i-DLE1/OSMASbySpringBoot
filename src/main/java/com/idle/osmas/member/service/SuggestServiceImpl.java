package com.idle.osmas.member.service;

import com.idle.osmas.member.dao.SuggestMapper;
import com.idle.osmas.member.dto.SuggestsDTO;
import com.idle.osmas.member.paging.SelectCriteria;
import com.idle.osmas.seller.dto.CategoryDTO;
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

    @Override
    public String selectContent(int no) {
        return mapper.selectContent(no);
    }

    @Override
    public int modifySuggest(SuggestsDTO suggest) {
        return mapper.modifySuggest(suggest);
    }

    @Override
    public int removeSuggest(int no) {
        return mapper.removeSuggest(no);
    }

    @Override
    public List<CategoryDTO> selectCategory() {
        return mapper.selectCategory();
    }
}
