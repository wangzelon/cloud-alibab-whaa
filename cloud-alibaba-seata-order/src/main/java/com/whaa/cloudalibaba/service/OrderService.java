package com.whaa.cloudalibaba.service;

/**
 * created by wangzelong 2020/3/25 20:22
 */
public interface OrderService {
    void orderService(Integer accountId, Integer productId, Integer num);

    void updateOrderStatus(Integer accountId);

}
