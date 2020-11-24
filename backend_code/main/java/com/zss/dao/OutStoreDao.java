package com.zss.dao;

import com.zss.po.Outstore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface OutStoreDao {

    //查询退货信息(模糊查询)
    List<Outstore> selectOutStore(@Param("outname") String outname,@Param("outtime") String outtime,@Param("outfactory") String outfactory);

    //退货
    int outStore(Outstore outstore);
}
