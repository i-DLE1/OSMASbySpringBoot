package com.idle.osmas.seller.dao;

import com.idle.osmas.seller.dto.ProjectQnAAnswerDTO;
import com.idle.osmas.seller.dto.ProjectQnADTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectQnAMapper {

    int deleteProjectQnAByProjectNo(int projectNo);


}
