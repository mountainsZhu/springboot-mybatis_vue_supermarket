package com.zss.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface MoneyDao {

    //修改超市和用户的金额
    int updateMoney(float cost);
}
