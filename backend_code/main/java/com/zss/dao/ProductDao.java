package com.zss.dao;

import com.zss.po.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ProductDao {

    //查询所有商品信息
    List<Product> selectAllProduct();

    //根据商品名查询
    List<Product> selectProductByName(String productName);

    //添加单个商品
    int insertProduct(Product product);

    //删除单个商品
    int deleteProduct(String productName);

    //修改单个商品
    int updateProduct(Product product);

    //根据商品名查找单价
    float selectPriceByProductName(String productname);
}
