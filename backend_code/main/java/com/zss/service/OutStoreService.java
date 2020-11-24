package com.zss.service;

import com.zss.po.Outstore;

import java.util.List;

public interface OutStoreService {

    //查询退货信息
    List<Outstore> selectOutstore(String outname,String outtime,String outfactory);

    //退货
    int outStore(Outstore outstore);
}
