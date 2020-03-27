package com.whaa.cloudalibaba.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.whaa.cloudalibaba.bean.Order;
import com.whaa.cloudalibaba.feign.AccountService;
import com.whaa.cloudalibaba.feign.StockService;
import com.whaa.cloudalibaba.mapper.OrderMapper;
import com.whaa.cloudalibaba.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * created by @author wangzelong 2020/3/25 20:23
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private AccountService accountService;
    @Autowired
    private StockService stockService;
    @Autowired
    private OrderMapper orderMapper;

    //    @GlobalTransactional(name = "tx_order", rollbackFor = Exception.class)
    @Override
    public void orderService(Integer accountId, Integer productId, Integer num) {
        Order order = new Order();
        order.setAccountId(accountId);
        order.setBuyNumber(num);
        order.setProductId(productId);
        BigDecimal price = new BigDecimal(productId * num);
        order.setTotalPrice(price);
        //创建订单
        orderMapper.insert(order);
        log.info("创建订单完成");
        //扣库存
        stockService.subStock(productId, num);
        log.info("扣减库存完成");
        //扣款
        accountService.subAccountMoney(accountId, price);
        log.info("扣减账户余额完成");
        order.setOrderStatus(1);
//        orderMapper.updateStatusId(order);
    }

    @Override
    public void updateOrderStatus(Integer accountId) {
//        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
//        orderQueryWrapper.eq("account_id", accountId);
//        orderQueryWrapper.eq("order_status", 0);
//        Order order = orderMapper.selectOne(orderQueryWrapper);
//        order.setOrderStatus(1);
//        orderMapper.updateById(order);
    }

}
