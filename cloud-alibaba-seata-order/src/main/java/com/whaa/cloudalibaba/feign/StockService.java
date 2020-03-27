package com.whaa.cloudalibaba.feign;

import com.whaa.common.constants.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * created by wangzelong 2020/3/25 17:06
 */
@FeignClient(value = "seata-stock")
public interface StockService {

    @PostMapping("/subStock")
    ResponseData subStock(@RequestParam("productId") Integer productId, @RequestParam("num") Integer num);
}
