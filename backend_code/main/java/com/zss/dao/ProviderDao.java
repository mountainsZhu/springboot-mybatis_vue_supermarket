package com.zss.dao;

import com.zss.po.Product;
import com.zss.po.Provider;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ProviderDao {

    //查询所有供应商信息
    List<Provider> selectAllProvider();

    //根据供应商名查询
    List<Provider> selectProviderByName(String providername);

    //添加单个供应商
    int insertProvider(Provider provider);

    //删除单个供应商
    int deleteProvider(String providername);

    //修改单个供应商
    int updateProvider(Provider provider);

}
