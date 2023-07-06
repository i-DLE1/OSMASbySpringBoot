package com.idle.osmas.member.service;

import com.idle.osmas.member.dao.MemberMapper;
import com.idle.osmas.member.dao.SuggestMapper;
import com.idle.osmas.member.dto.SuggestsDTO;
import com.idle.osmas.member.paging.SelectCriteria;
import com.idle.osmas.seller.dto.CategoryDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class SuggestServiceImpl implements SuggestService{

    private final SuggestMapper mapper;
    private final MemberMapper memberMapper;

    public SuggestServiceImpl(SuggestMapper mapper, MemberMapper memberMapper)
    {
        this.mapper = mapper;
        this.memberMapper = memberMapper;
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

    @Override
    @Transactional
    public int suggestWrite(String name, String title, String content, String id) {
        int refMemberNo = memberMapper.selectNoByNickname(memberMapper.selectNicknameById(id));
        System.out.println("=========================");
        System.out.println(refMemberNo);
        System.out.println("=========================");
        int refCategoryNo = mapper.selectCategoryNoByName(name);
        System.out.println(refCategoryNo);
        SuggestsDTO suggest = new SuggestsDTO();
        suggest.setContent(content);
        suggest.setTitle(title);
        suggest.setRefCategoryNo(refCategoryNo);
        suggest.setRefMemberNo(refMemberNo);
        int result = mapper.suggestWrite(suggest);
        return result;
    }
}
