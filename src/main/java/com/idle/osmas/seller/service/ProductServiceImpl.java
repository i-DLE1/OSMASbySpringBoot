package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dao.ProductMapper;
import com.idle.osmas.seller.dto.ProductDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    @Transactional
    public int insertProjectProduct(List<ProductDTO> productList) {
        if(productList.size() > 0) return productMapper.insertProjectProduct(productList);
        return 1;
    }

    @Override
    public int updateProjectProduct(List<ProductDTO> productList) {
        if(productList.size() > 0) return productMapper.updateProjectProduct(productList);
        return 1;
    }

    @Override
    @Transactional
    public int deleteProjectProduct(List<ProductDTO> productList) {
        int result = 0;
        for (ProductDTO e : productList) {
            int subResult = 0;
            subResult += productMapper.deleteProjectProductList(e.getNo());
            subResult += productMapper.deleteProductByProductNo(e.getNo());
            if(subResult == 2) result++;
        }
        if(result == productList.size()) return 1;

        return 0;
    }

    @Override
    public List<ProductDTO> selectProductListByProjectNo(int projectNo, int userNo) {

        return productMapper.selectProductListByProjectNo(projectNo, userNo);
    }

    @Override
    public int selectProductListCountByProjectNo(int projectNo) {
        return productMapper.selectProductListCountByProjectNo(projectNo);
    }

    @Override
    public List<ProductDTO> selectSponsoredPrjByProjectNo(int projectNo, int userNo) {
        return productMapper.selectSponsoredPrjByProjectNo(projectNo, userNo);
    }
}
