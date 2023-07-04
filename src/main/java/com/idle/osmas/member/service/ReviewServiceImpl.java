package com.idle.osmas.member.service;

import com.idle.osmas.member.dao.ReviewMapper;
import com.idle.osmas.member.dto.ReviewsDTO;
import com.idle.osmas.member.dto.SponsoredsDTO;
import com.idle.osmas.member.paging.SelectCriteria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReviewServiceImpl implements ReviewService{

    private final ReviewMapper mapper;

    public ReviewServiceImpl(ReviewMapper mapper){
        this.mapper = mapper;
    }
    @Override
    public int selectTotalCount(Map<String, String> searchMap) {

        int result = mapper.selectTotalCount(searchMap);
        return result;
    }

    @Override
    public int selectTotalCountSponsored(String id) {
        return mapper.selectTotalCountSponsored(id);
    }

    @Override
    public List<ReviewsDTO> selectReviewList(SelectCriteria selectCriteria) {

        List<ReviewsDTO> reviewList = mapper.selectReviewList(selectCriteria);
        return reviewList;
    }

    @Override
    public List<SponsoredsDTO> selectSponsoredList(SelectCriteria selectCriteria) {
        return mapper.selectSponsoredList(selectCriteria);
    }

    @Override
    public String selectContent(int no) {
        return mapper.selectContent(no);
    }
}
