package com.idle.osmas.member.service;

import com.idle.osmas.member.dto.ReviewsDTO;
import com.idle.osmas.member.paging.SelectCriteria;

import java.util.List;
import java.util.Map;

public interface ReviewService {

    public int selectTotalCount(Map<String, String> searchMap);

    public List<ReviewsDTO> selectReviewList(SelectCriteria selectCriteria);
}
