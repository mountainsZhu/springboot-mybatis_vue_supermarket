package com.zss.dao;

import com.zss.po.Singlesale;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.List;

@Component
@Mapper
public interface SinglesaleDao {

    //查询所有的单笔销售情况
    List<Singlesale> selectAllSinglesale();

    //模糊查询销售情况
    List<Singlesale> selectSinglesale(String time);

    //添加单个商品记录
    int insertSingle(@Param("salename") String salename,@Param("saleamount") int saleamount,@Param("saleprice") float saleprice,@Param("sumprice") float sumprice);

}
