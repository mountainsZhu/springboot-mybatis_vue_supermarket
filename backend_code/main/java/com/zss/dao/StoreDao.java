package com.zss.dao;

import com.zss.po.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface StoreDao {

    //带条件查询所有物品
    List<Store> selectAllStore(@Param("storetime") String storetime,@Param("storename") String storename);

    //无条件查询所有物品
    List<Store> selectAllStore2();

    //商品上架
    int upProduct(String storeName);

    //商品下架
    int downProduct(String storeName);

    //查询是否有积货现象
    List<Store> isUpper();

    //查询是否有缺货现象
    List<Store> isLower();

    //修改积货上限
    int updateUpper(@Param("storeName") String storeName,@Param("upperlimit") int upperlimit);

    //修改缺货下限
    int updateLower(@Param("storeName") String storeName,@Param("lowerlimit") int lowerlimit);

    //查询所有有损坏/变质的货物
    List<Store> selectAllBadStore();

    //处理变质、损坏
    int handleBadStore(@Param("storeName") String storeName,@Param("handleamount") int handleamount);

    //进/退货(修改数量)
    int updateStore(@Param("storeName") String storeName, @Param("amount") int amount, @Param("storetime") java.util.Date storetime);

    //进货(添加商品)
    int insertStore(Store store);

}
