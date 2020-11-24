package com.zss.controller;

import com.alibaba.fastjson.JSONObject;
import com.zss.SaleMsg;
import com.zss.po.Singlesale;
import com.zss.service.SinglesaleService;
import com.zss.util.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class SinglesaleController {

    @Autowired
    private SinglesaleService singlesaleService;


    //查询所有销售信息
    @RequestMapping("selectAllSinglesale")
    @ResponseBody
    List<Singlesale> selectAllSinglesale(){
        return singlesaleService.selectAllSinglesale();
    }

    //收银台
    @RequestMapping("insertSale")
    @ResponseBody
    int insertSale(@RequestBody(required = false) Map<String,Object> map) throws Exception {
        List<SaleMsg> list = (ArrayList) map.get("sale");
//        Iterator iterator = list.iterator();
//        while(iterator.hasNext()){
//            LinkedHashMap list1 = (LinkedHashMap) iterator.next();
//            System.out.println(list1);
//            System.out.println(list1.get("salename"));
//            System.out.println(list1.get("saleamount"));
//            System.out.println(list1.get("saleprice"));
//            System.out.println(list1.get("salecost"));
//        }

        String costStr = String.valueOf(map.get("cost"));
        float cost = Float.valueOf(costStr);

        return singlesaleService.insertSale(list,cost);
    }
}
