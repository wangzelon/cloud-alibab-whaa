package com.whaa.cloudalibaba.controller;

import com.whaa.cloudalibaba.mapper.AccountMapper;
import com.whaa.common.constants.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * created by @author wangzelong 2020/3/25 17:08
 */
@RestController
public class AccountController {
    @Autowired
    private AccountMapper accountMapper;

    @PostMapping("/subAccountMoney")
    public ResponseData subAccountMoney(Integer accountId, BigDecimal price) {
        accountMapper.updateAccount(accountId, price);
        return ResponseData.success();
    }
}
