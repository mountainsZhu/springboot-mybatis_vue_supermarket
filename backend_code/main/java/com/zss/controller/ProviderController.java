package com.zss.controller;

import com.zss.dao.ProviderDao;
import com.zss.po.Product;
import com.zss.po.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProviderController {

    @Autowired
    private ProviderDao providerDao;

    //查询所有供应商
    @RequestMapping("selectAllProvider")
    @ResponseBody
    public List<Provider> selectAllProvider(){
        return providerDao.selectAllProvider();
    }

    //通过供应商名查询供应商
    @RequestMapping("selectProviderByName")
    @ResponseBody
    public List<Provider> selectProviderByName(String providername){
        //System.out.println(productname);
        return providerDao.selectProviderByName(providername);
    }

    //添加单个供应商
    @RequestMapping("insertProvider")
    @ResponseBody
    public int insertProvider(Provider provider){
        int effectRow = 0;
        if((providerDao.selectProviderByName(provider.getProvidername())).size()==0){
            effectRow = providerDao.insertProvider(provider);
        }
        return effectRow;
    }

    //删除单个供应商
    @RequestMapping("deleteProvider")
    @ResponseBody
    public int deleteProvider(String providername){
        return providerDao.deleteProvider(providername);
    }

    //修改单个供应商
    @RequestMapping("updateProvider")
    @ResponseBody
    public int updateProvider(Provider provider){
        return providerDao.updateProvider(provider);
    }
}
