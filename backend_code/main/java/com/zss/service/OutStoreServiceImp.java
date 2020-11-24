package com.zss.service;

import com.zss.dao.OutStoreDao;
import com.zss.dao.StoreDao;
import com.zss.po.Outstore;
import com.zss.po.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OutStoreServiceImp implements OutStoreService{

    @Autowired
    private OutStoreDao outStoreDao;
    @Autowired
    private StoreDao storeDao;

    @Override
    public List<Outstore> selectOutstore(String outname,String outtime,String outfactory) {
        return outStoreDao.selectOutStore(outname,outtime,outfactory);
    }

    @Transactional
    @Override
    public int outStore(Outstore outstore) {
        int flag = 0;
        //退货表增加信息
        outStoreDao.outStore(outstore);
        //修改库存
        flag += storeDao.updateStore(outstore.getOutname(),-outstore.getOutamount(),outstore.getOuttime());
        return flag;
    }
}
