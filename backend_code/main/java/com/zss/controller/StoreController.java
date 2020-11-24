package com.zss.controller;

import com.zss.dao.StoreDao;
import com.zss.po.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class StoreController {

    @Autowired
    private StoreDao storeDao;

    //有条件查询所有物品
    @RequestMapping("selectAllStore")
    @ResponseBody
    List<Store> selectAllStore(String storetime,String storename){
        return storeDao.selectAllStore(storetime,storename);
    }

    //无条件查询所有物品
    @RequestMapping("selectAllStore2")
    @ResponseBody
    List<Store> selectAllStore2(){
        return storeDao.selectAllStore2();
    }

    //商品上架
    @RequestMapping("upProduct")
    @ResponseBody
    int upProduct(String storeName){
        System.out.println(storeName);
        return storeDao.upProduct(storeName);
    }

    //商品下架
    @RequestMapping("downProduct")
    @ResponseBody
    int downProduct(String storeName){
        return storeDao.downProduct(storeName);
    }

    //查询是否有积货现象
    @RequestMapping("isUpper")
    @ResponseBody
    List<Store> isUpper(){
        return storeDao.isUpper();
    }

    //查询是否有缺货现象
    @RequestMapping("isLower")
    @ResponseBody
    List<Store> isLower(){
        return storeDao.isLower();
    }

    //修改积货上限
    @RequestMapping("updateUpper")
    @ResponseBody
    int updateUpper(String storeName,int upperlimit){
        return storeDao.updateUpper(storeName,upperlimit);
    }

    //修改缺货下限
    @RequestMapping("updateLower")
    @ResponseBody
    int updateLower(String storeName,int lowerlimit){
        return storeDao.updateLower(storeName,lowerlimit);
    }

    //查询所有变质/损坏的货物
    @RequestMapping("selectAllBadStore")
    @ResponseBody
    List<Store> selectAllBadStore(){
        return storeDao.selectAllBadStore();
    }

    //处理变质、损坏
    @RequestMapping("handleBadStore")
    @ResponseBody
    int handleBadStore(String storeName,int handleamount){

        return storeDao.handleBadStore(storeName,handleamount);
    }

}
