package com.whaa.cloudalibaba.feign;

import com.whaa.common.constants.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * created by wangzelong 2020/3/25 17:07
 */
@FeignClient("seata-account")
public interface AccountService {

    @PostMapping("/subAccountMoney")
    ResponseData subAccountMoney(@RequestParam("accountId") Integer accountId, @RequestParam("price") BigDecimal price);
}
