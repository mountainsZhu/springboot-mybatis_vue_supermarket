package com.zss.service;

import com.zss.SaleMsg;
import com.zss.dao.MoneyDao;
import com.zss.dao.SinglesaleDao;
import com.zss.po.Singlesale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class SinglesaleServiceImp implements SinglesaleService{

    @Autowired
    private SinglesaleDao singlesaleDao;
    @Autowired
    private MoneyDao moneyDao;

    @Override
    public List<Singlesale> selectAllSinglesale() {
        return singlesaleDao.selectAllSinglesale();
    }

    @Override
    public List<Singlesale> selectSinglesale(String saletime) {
        return singlesaleDao.selectSinglesale(saletime);
    }

    //查询月报表
    @Override
    public List<Singlesale> selectSaleByMonth(String year, String month) {
        String str = year + "-" +month;
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
//        Date date = null;
//        try {
//            date = sdf.parse(str);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        return singlesaleDao.selectSinglesale(str);
    }

    //查询年报表
    @Override
    public List<Singlesale> selectSaleByYear(String year) {
        String str = year;
        return singlesaleDao.selectSinglesale(str);
    }

    //收银台
    @Transactional
    @Override
    public int insertSale(List<SaleMsg> list, float cost) {
        int flag = 0;
        //金钱变化
        moneyDao.updateMoney(cost);
        //表单变化
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            LinkedHashMap list1 = (LinkedHashMap) iterator.next();
            String salename = (String) list1.get("salename");
            int saleamount = Integer.valueOf(list1.get("saleamount").toString());
            float saleprice = Float.valueOf(list1.get("saleprice").toString());
            float sumprice = Float.valueOf(list1.get("salecost").toString());
            flag += singlesaleDao.insertSingle(salename,saleamount,saleprice,sumprice);
        }
        return flag;
    }
}
