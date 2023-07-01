package com.idle.osmas.member.service;

import com.idle.osmas.member.dao.ReviewMapper;
import com.idle.osmas.member.dto.ReviewsDTO;
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
    public List<ReviewsDTO> selectReviewList(SelectCriteria selectCriteria) {

        List<ReviewsDTO> reviewList = mapper.selectReviewList(selectCriteria);
        return reviewList;

    }
}
