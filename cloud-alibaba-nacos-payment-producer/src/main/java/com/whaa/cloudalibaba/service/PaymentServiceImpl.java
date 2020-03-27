package com.whaa.cloudalibaba.service;

import com.whaa.common.service.PaymentService;
import org.apache.dubbo.config.annotation.Service;

/**
 * created by @author wangzelong 2020/3/23 18:50
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public String findByString(String name) {
        return "输入的参数" + name;
    }
}
