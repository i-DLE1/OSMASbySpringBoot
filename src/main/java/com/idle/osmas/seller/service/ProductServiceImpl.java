package com.idle.osmas.seller.service;

import com.idle.osmas.seller.dao.ProductMapper;
import com.idle.osmas.seller.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public int deleteProductListByProjectNo(int projectNo) {
        return 0;
    }

    @Override
    public int deleteProductByProductNo(int productNo) {
        return 0;
    }

    @Override
    public int insertProjectProduct(List<ProductDTO> productList, String userId, int projectNo) {
        productList.forEach( e ->{
            if(e.getNo() == 0){
                productMapper.insertProjectProduct(e);
                productMapper.insertProjectProductList(projectNo, e.getNo());
            }else {
                productMapper.updateProjectProduct(e);
            }
        });

        return 1;
    }

    @Override
    public int deleteProjectProduct(List<ProductDTO> productList) {
        productList.forEach(e->{
            productMapper.deleteProjectProductList(e.getNo());
            productMapper.deleteProjectProduct(e.getNo());
        });
        return 0;
    }

    @Override
    public List<ProductDTO> selectProductListByProjectNo(int projectNo, String userId) {

        return productMapper.selectProductListByProjectNo(projectNo, userId);
    }
}
