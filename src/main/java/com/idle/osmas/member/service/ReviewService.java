package com.idle.osmas.member.service;

import com.idle.osmas.member.dto.ReviewsDTO;
import com.idle.osmas.member.dto.SponsoredsDTO;
import com.idle.osmas.member.paging.SelectCriteria;

import java.util.List;
import java.util.Map;

public interface ReviewService {
    // review의 총 갯수
    public int selectTotalCount(Map<String, String> searchMap);

    public int selectTotalCountSponsored(String id);
    public List<ReviewsDTO> selectReviewList(SelectCriteria selectCriteria);

    public List<SponsoredsDTO> selectSponsoredList(SelectCriteria selectCriteria);

    public String selectContent(int no);
}
