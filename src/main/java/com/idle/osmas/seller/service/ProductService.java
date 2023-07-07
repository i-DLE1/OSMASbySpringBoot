package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    int insertProjectProduct(List<ProductDTO> productList);

    int updateProjectProduct(List<ProductDTO> productList);

    int deleteProjectProduct(List<ProductDTO> productList);

    List<ProductDTO> selectProductListByProjectNo(int projectNo, int userNo);

    int selectProductListCountByProjectNo(int projectNo);

    List<ProductDTO> selectSponsoredPrjByProjectNo(int projectNo, int userNo);




}

