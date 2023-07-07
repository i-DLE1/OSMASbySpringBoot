package com.idle.osmas.seller.dao;

import com.idle.osmas.seller.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<ProductDTO> selectProductListByProjectNo(int projectNo, int userNo);

    int selectProductListCountByProjectNo(int projectNo);

    List<ProductDTO> selectSponsoredPrjByProjectNo(int projectNo, int userNo);

//    int insertProjectProduct(ProductDTO product, int projectNo);
    int insertProjectProduct(List<ProductDTO> productList);

    int updateProjectProduct(List<ProductDTO> productList);

    int deleteProductByProductNo(int productNo);

    int deleteProjectProductList(int productNo);

}
