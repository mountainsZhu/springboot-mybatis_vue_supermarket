package com.zss.service;

import com.zss.SaleMsg;
import com.zss.po.Singlesale;

import java.util.Date;
import java.util.List;

public interface SinglesaleService {

    //查询所有销售情况
    List<Singlesale> selectAllSinglesale();

    //模糊查询销售情况
    List<Singlesale> selectSinglesale(String time);

    //根据月份查询
    List<Singlesale> selectSaleByMonth(String year,String month);

    //根据年份查询
    List<Singlesale> selectSaleByYear(String year);

    //收银台
    int insertSale(List<SaleMsg> list, float cost);

}
