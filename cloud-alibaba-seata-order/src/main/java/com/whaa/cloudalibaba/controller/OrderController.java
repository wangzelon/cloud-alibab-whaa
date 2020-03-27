package com.whaa.cloudalibaba.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.whaa.cloudalibaba.bean.Order;
import com.whaa.cloudalibaba.feign.AccountService;
import com.whaa.cloudalibaba.feign.StockService;
import com.whaa.cloudalibaba.mapper.OrderMapper;
import com.whaa.cloudalibaba.service.OrderService;
import com.whaa.common.constants.CodeConstant;
import com.whaa.common.constants.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * created by @author wangzelong 2020/3/25 17:00
 */
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/createOrder")
    public ResponseData createOrder(Integer accountId, Integer productId, Integer num) {
        orderService.orderService(accountId, productId, num);
        return ResponseData.success();
    }

}
