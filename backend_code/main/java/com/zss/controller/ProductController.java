package com.zss.controller;

import com.zss.dao.ProductDao;
import com.zss.po.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductDao productDao;

    //查询所有物品
    @RequestMapping("selectAllProduct")
    @ResponseBody
    public List<Product> selectAllProduct(){
        return productDao.selectAllProduct();
    }

    //通过物品名查询物品
    @RequestMapping("selectProductByName")
    @ResponseBody
    public List<Product> selectProductByName(String productname){
//        System.out.println(productname);
        return productDao.selectProductByName(productname);
    }

    //添加单个商品
    @RequestMapping("insertProduct")
    @ResponseBody
    public int insertProduct(Product product){
        int effectRow = 0;
        if((productDao.selectProductByName(product.getProductname())).size()==0){
            effectRow = productDao.insertProduct(product);
        }
        return effectRow;
    }

    //删除单个商品
    @RequestMapping("deleteProduct")
    @ResponseBody
    public int deleteProduct(String productname){
        return productDao.deleteProduct(productname);
    }

    //修改单个商品
    @RequestMapping("updateProduct")
    @ResponseBody
    public int updateProduct(Product product){
        return productDao.updateProduct(product);
    }

    ////根据商品名查找单价
    @RequestMapping("selectPriceByProductName")
    @ResponseBody
    public float selectPriceByProductName(String productname){
        return productDao.selectPriceByProductName(productname);
    }
}
