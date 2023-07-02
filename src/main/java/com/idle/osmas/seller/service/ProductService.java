package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    int deleteProductListByProjectNo(int projectNo);

    int deleteProductByProductNo(int productNo);

    int insertProjectProduct(List<ProductDTO> productList, String userId, int projectNo);

    int deleteProjectProduct(List<ProductDTO> productList);

    List<ProductDTO> selectProductListByProjectNo(int projectNo, String userId);


}

