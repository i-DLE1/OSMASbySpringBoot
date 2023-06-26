package com.idle.osmas.seller.dao;

import com.idle.osmas.seller.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<ProductDTO> selectAllProductByProjectId(int projectNo); // 프로젝트 아이디로 모든 상품 리스트 조회

    int insertProductByProjectId(int projectNo, ProductDTO product); // 프로젝트 no으로 상품리스트 추가

    int updateProductByid(int productNo, ProductDTO product); // 상품 no으로 상품 정보 수정

}
