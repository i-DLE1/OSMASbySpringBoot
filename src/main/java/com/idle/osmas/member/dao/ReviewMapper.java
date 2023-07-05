package com.idle.osmas.member.dao;

import com.idle.osmas.member.dto.ReviewDTO;
import com.idle.osmas.member.dto.ReviewsDTO;
import com.idle.osmas.member.dto.SponsoredsDTO;
import com.idle.osmas.member.paging.SelectCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReviewMapper {
    int selectTotalCount(Map<String, String> searchMap);

    int selectTotalCountSponsored(String id);
    List<ReviewsDTO> selectReviewList(SelectCriteria selectCriteria);

    List<SponsoredsDTO> selectSponsoredList(SelectCriteria selectCriteria);

    String selectContent(int no);

    int modifyReview(ReviewsDTO review);

    int removeReview(int no);

    int selectDeliveryStatusCodeistNoBySponsoredNo(int SponsoredNo);

    int writeReview(ReviewDTO review);
}
