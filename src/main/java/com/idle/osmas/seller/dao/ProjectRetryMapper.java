package com.idle.osmas.seller.dao;

import com.idle.osmas.seller.dto.ProjectProgressDTO;

public interface ProjectRetryMapper {

    ProjectProgressDTO selectById(int no);
    ProjectProgressDTO insertProjectRetry(ProjectProgressDTO projectProgress);
}
