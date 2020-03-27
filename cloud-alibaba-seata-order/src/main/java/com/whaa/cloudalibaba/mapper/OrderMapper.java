package com.whaa.cloudalibaba.mapper;

import com.whaa.cloudalibaba.bean.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * created by wangzelong 2020/3/25 16:56
 */
@Mapper
public interface OrderMapper {
    void insert(Order order);
}
