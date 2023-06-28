package com.idle.osmas.seller.dao;

import com.idle.osmas.seller.dto.ProductDTO;
import com.idle.osmas.seller.dto.ProjectDTO;

public interface SalesMapper {

    ProjectDTO selectProjectByNo(int no);

}
