package com.whaa.cloudalibaba.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;

/**
 * created by wangzelong 2020/3/25 16:51
 */
@Mapper
public interface AccountMapper {
    /**
     * 更新
     * @param accountId
     * @param price
     * @return
     */
    @Update("update account set use_money=use_money+ #{price} where account_id=#{accountId}")
    Integer updateAccount(@Param("accountId") Integer accountId, @Param("price") BigDecimal price);

}
