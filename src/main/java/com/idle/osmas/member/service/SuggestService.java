package com.idle.osmas.member.service;

import com.idle.osmas.member.dto.SuggestsDTO;
import com.idle.osmas.member.paging.SelectCriteria;
import com.idle.osmas.seller.dto.CategoryDTO;

import java.util.List;
import java.util.Map;

public interface SuggestService {

    public int selectTotalCount(Map<String, String> searchMap);

    public List<SuggestsDTO> selectSuggestList(SelectCriteria selectCriteria);

    public String selectContent(int no);
    public int modifySuggest(SuggestsDTO suggest);

    public int removeSuggest(int no);

    public List<CategoryDTO> selectCategory();
}
