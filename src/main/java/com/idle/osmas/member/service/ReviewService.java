package com.idle.osmas.member.service;

import com.idle.osmas.member.dto.ReviewDTO;
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

    public int modifyReview(ReviewsDTO review);

    public int removeReview(int no);
    // 후원한프로젝트 코드로 배송이력코드 조회
    public int selectDeliveryStatusCodeistNoBySponsoredNo(int SponsoredNo);

    public int writeReview(ReviewDTO review) throws Exception;
}
