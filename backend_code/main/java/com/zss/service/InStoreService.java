package com.zss.service;

import com.zss.po.Instore;

import java.util.List;

public interface InStoreService {

    //查询进货信息
    List<Instore> selectInstore(String inname,String intime,String infactory);

    //进货
    int inStore(Instore instore,float promoteprice,int guarantee,String providername,String phone,String email);

    //进货2
    int inStore2(Instore instore);
}
