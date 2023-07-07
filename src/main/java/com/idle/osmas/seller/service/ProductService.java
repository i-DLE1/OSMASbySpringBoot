package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dto.ProductDTO;
import com.idle.osmas.seller.dto.ProjectDTO;

import java.util.List;
import java.util.Map;

public interface ProductService {

    int insertProjectProduct(List<ProductDTO> productList, int projectNo);

    int deleteProjectProduct(List<ProductDTO> productList);

    List<ProductDTO> selectProductListByProjectNo(int projectNo, int userNo);

    int selectProductListCountByProjectNo(int projectNo);

    List<ProductDTO> selectSponsoredPrjByProjectNo(int projectNo, int userNo);


}

