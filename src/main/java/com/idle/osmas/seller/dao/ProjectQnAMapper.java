package com.idle.osmas.seller.dao;

import com.idle.osmas.seller.dto.ProjectQnAAnswerDTO;
import com.idle.osmas.seller.dto.ProjectQnADTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectQnAMapper {

    List<ProjectQnADTO> selectAllProjectQnAByMemberNo(int memberNo); // 멤버 아이디로 속한 모든 프로젝트 Q/A 검색

    ProjectQnADTO selectByProjectQnAId(int id); // 프로젝트QnA pk로 검색

    ProjectQnAAnswerDTO selectByProjectQnAAnswerQnAId(int id); // 프로젝트 QnA pk로 검색

    int insertByProjectQnAAnswerId(int projectQnAno, int memberNo, ProjectQnAAnswerDTO projectQnAAnswer) ;

    int updateByProjectQnAAnswerId(int id, ProjectQnAAnswerDTO projectQnAAnswerDTO);



}
