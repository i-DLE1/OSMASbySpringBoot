package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dto.ProjectDTO;

import java.util.List;

public interface ProejctService {

    public List<ProjectDTO> selectAllByAccount(int refMemberNo); // 프로젝트 계정 no으로 조회

    public List<ProjectDTO> selectAllProject();

}
