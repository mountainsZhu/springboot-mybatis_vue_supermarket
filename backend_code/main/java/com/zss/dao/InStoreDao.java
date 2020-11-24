package com.zss.dao;

import com.zss.po.Instore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface InStoreDao {

    //查询进货信息(模糊查询)
    List<Instore> selectInstore(@Param("inname") String inname,@Param("intime") String intime,@Param("infactory") String infactory);

    //进货
    int inStore(Instore instore);
}
