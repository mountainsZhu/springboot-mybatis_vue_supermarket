package com.zss.service;

import com.zss.dao.InStoreDao;
import com.zss.dao.ProductDao;
import com.zss.dao.ProviderDao;
import com.zss.dao.StoreDao;
import com.zss.po.Instore;
import com.zss.po.Product;
import com.zss.po.Provider;
import com.zss.po.Store;
import org.apache.ibatis.annotations.Param;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InStoreServiceImp implements InStoreService{

    @Autowired
    private ProductDao productDao;
    @Autowired
    private InStoreDao inStoreDao;
    @Autowired
    private StoreDao storeDao;
    @Autowired
    private ProviderDao providerDao;

    @Override
    public List<Instore> selectInstore(String inname,String intime,String infactory) {
        System.out.println(inname);
        System.out.println(intime);
        System.out.println(infactory);
        return inStoreDao.selectInstore(inname,intime,infactory);
    }

    @Transactional
    @Override
    public int inStore(Instore instore, float promoteprice, int guarantee, String provideraddress, String phone, String email) {
        int flag = 0;
        //添加供应商
        Provider provider = new Provider(instore.getInfactory(),phone,provideraddress,email);
        flag += providerDao.insertProvider(provider);

        //在product中添加商品
        Product product = new Product(instore.getInname(),instore.getInprice(),promoteprice,instore.getInfactory(),instore.getIntime(),guarantee);
        flag += productDao.insertProduct(product);
        //添加库存
        Store store = new Store(instore.getIntime(),instore.getInname(),instore.getInamount());
        flag += storeDao.insertStore(store);

        //入库表添加入库信息
        inStoreDao.inStore(instore);

        return flag;
    }

    @Transactional
    @Override
    public int inStore2(Instore instore){
        inStoreDao.inStore(instore);
        return storeDao.updateStore(instore.getInname(),instore.getInamount(), instore.getIntime());
    }
}
