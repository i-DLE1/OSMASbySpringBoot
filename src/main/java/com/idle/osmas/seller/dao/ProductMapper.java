package com.idle.osmas.seller.dao;

import com.idle.osmas.seller.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

//    List<ProductDTO> selectProductListByProjectNo(int projectNo);
    int deleteProductListByProjectNo(int projectNo);

    int deleteProductByProductNo(int productNo);

    List<ProductDTO> selectProductListByProjectNo(int projectNo, String userId);

    int insertProjectProduct(ProductDTO product);

    int insertProjectProductList(int projectNo , int productNo);

    int updateProjectProduct(ProductDTO product);

    int deleteProjectProduct(int productNo);

    int deleteProjectProductList(int productNo);

}
